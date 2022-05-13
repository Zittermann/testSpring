package com.example.demo.service;

import com.example.demo.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {

        // Test config
        Book book = new Book(1L, "Clean Code", "Author", 1000, 49.99, LocalDate.now(), true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        // Se ejecuta comportamiento bajo testing
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        // Comprobacion asesoria
        assertTrue(price > 0);
        assertEquals(57.980000000000004, price);

    }
}