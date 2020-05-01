package frgp.utn.edu.ar.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import frgp.utn.edu.ar.main.MatrizAdyacencia;

public class MatrizAdyacenciaTest {
	private final Integer totalElements = 10;
	protected MatrizAdyacencia matrizAdyacencia = new MatrizAdyacencia(totalElements);

	@Test
	void agregarElementoSimetriaTest() {
		final Integer column = 2;
		final Integer row = 5;

		matrizAdyacencia.agregarElemento(column, row);
		assertTrue(matrizAdyacencia.existeElemento(column, row));
		assertTrue(matrizAdyacencia.existeElemento(row, column));
		assertFalse(matrizAdyacencia.existeElemento(0, 0));
	}

	@Test
	void existenTodosLosElementoTest() {
		// Genera automáticamente una lista de 0 a (totalElements - 1) elementos.
		ArrayList<Integer> totalVector = (ArrayList<Integer>) IntStream.rangeClosed(0, totalElements - 1).boxed().collect(Collectors.toList());

		totalVector.forEach(column -> totalVector.forEach(row -> matrizAdyacencia.agregarElemento(column, row)));

		totalVector.forEach(column -> totalVector.forEach(row -> assertTrue(matrizAdyacencia.existeElemento(column, row))));
		totalVector.forEach(column -> totalVector.forEach(row -> assertTrue(matrizAdyacencia.existeElemento(row, column))));

		assertEquals(50, matrizAdyacencia.getCantidadElementos());
	}

	@Test
	void agregarElementoFueraRangoTest() {
		final Integer column = totalElements;
		final Integer row = 5;

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrizAdyacencia.agregarElemento(column, row));
	}

	@Test
	public void agregarElementoTest() {
		final Integer column = 2;
		final Integer row = 5;

		matrizAdyacencia.agregarElemento(column, row);
		assertTrue(matrizAdyacencia.existeElemento(column, row));
	}

	@Test
	public void eliminarElementoTest() {
		final Integer column = 2;
		final Integer row = 5;

		matrizAdyacencia.agregarElemento(column, row);
		assertTrue(matrizAdyacencia.existeElemento(column, row));
		matrizAdyacencia.eliminarElemento(column, row);
		assertFalse(matrizAdyacencia.existeElemento(column, row));
	}
	
	@Tag("Eliminacion")
	@Test
	public void eliminarElementoSimetricoTest () {
		
		//VARIABLES 
		final Integer column = 2;
		final Integer row = 5;
		
		//AGREGO ELEMENTO Y SU SIMETRICO
		matrizAdyacencia.agregarElemento(column, row);
		
		//SE AGREGO EL ELEMENTO Y SU SIMETRICO?
		assertTrue(matrizAdyacencia.existeElemento(column, row));
		assertTrue(matrizAdyacencia.existeElemento(row,column));
		
		//ELIMINO EL ELEMENTO Y SU SIMETRICO
		matrizAdyacencia.eliminarElemento(column, row);
		matrizAdyacencia.eliminarElemento(row,column);
		
		//VERIFICO SI EL ELEMENTO Y SU SIMETRICO SE ELIMINARON
		assertFalse(matrizAdyacencia.existeElemento(column, row));
	}

	@Test
	public void agregarElementoFilaNegativaTest () {
		final Integer column = 2;
		final Integer row = -5;

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrizAdyacencia.agregarElemento(column, row));
	}
	
	@Tag("Agregar")
	@Test
	public void agregarElementoColumnaNegativaTest () {
		
		//VARIABLE
		final Integer column = -2;
		final Integer row = 5;
		
		//VERIFICO QUE TIRE UNA EXCEPCION DE ESTE TIPO
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrizAdyacencia.agregarElemento(row, column));
	}

	@Test
	public void contarRelacionesTest () {
		final Integer column1 = 1;
		final Integer column2 = 2;
		final Integer column3 = 3;
		final Integer row1 = 3;
		final Integer row2 = 4;
		final Integer row3 = 5;

		matrizAdyacencia.agregarElemento(column1, row1);
		matrizAdyacencia.agregarElemento(column2, row2);
		matrizAdyacencia.agregarElemento(column3, row3);
		assertEquals(3, matrizAdyacencia.getCantidadElementos());
	}
}
