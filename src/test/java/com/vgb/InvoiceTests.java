package com.vgb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit test suite for the Invoice system in VGB.
 */
public class InvoiceTests {
    public static final double TOLERANCE = 0.001;

    //Dummy Company
    List<String> email = new ArrayList<>();
	Material material = new Material(UUID.fromString("7af2d8f9-d09e-4992-a41d-3bec9ed2aa31"),"Cement", "kg", 40.0, 32.00);
	Person person = new Person(UUID.fromString("7af2d8f9-d09e-4992-a41d-3bec9ed2aa31"), "Might", "KMS", "60527", email);
	Address address = new Address("Huntington Ave", "Lincoln", "NE", "6887");
	Company company = new Company("John Doe agency", UUID.fromString("3437913d-eb38-48e2-97e3-cccce421cdb1"), person, address);
    
    /**
     * Tests the subtotal, tax total, and grand total values of an invoice.
     */
    @Test
    public void testInvoice01() {
        // Create sample invoice items (Equipment, Lease, Rental)
        Equipment equipment = new Equipment(UUID.randomUUID(), "Backhoe 3000", "BH30X2", 95125.00);
        Lease lease = new Lease(equipment, LocalDate.of(2024, 1, 1), LocalDate.of(2026, 6, 1));
        Rental rental = new Rental(equipment, 
        	    LocalDate.of(2023, 5, 1).atStartOfDay(), 
        	    LocalDate.of(2023, 5, 1).atStartOfDay()
        	);

        // Create InvoiceItem instances
        InvoiceItem item1 = new InvoiceItem(equipment);
        InvoiceItem item2 = new InvoiceItem(lease);
        InvoiceItem item3 = new InvoiceItem(rental);

        // Create an invoice and add items
        Invoice invoice = new Invoice(UUID.randomUUID(), company, person, LocalDate.now());
        invoice.addItem(item1);
        invoice.addItem(item2);
        invoice.addItem(item3);

        // Expected values
        double expectedSubtotal = equipment.getPrice() + lease.calculateAgreement() + rental.calculateAgreement();
        double expectedTaxTotal = equipment.calculateTax() + lease.calculateTax() + rental.calculateTax();
        double expectedGrandTotal = expectedSubtotal + expectedTaxTotal;

        // Actual values from invoice methods
        double actualSubtotal = invoice.calculateSubtotal();
        double actualTaxTotal = invoice.calculateTaxTotal();
        double actualGrandTotal = invoice.calculateGrandTotal();

        // Assert results
        assertEquals(expectedSubtotal, actualSubtotal, TOLERANCE);
        assertEquals(expectedTaxTotal, actualTaxTotal, TOLERANCE);
        assertEquals(expectedGrandTotal, actualGrandTotal, TOLERANCE);
        
        String invoiceString = invoice.toString();
        assertTrue(invoiceString.contains("Invoice UUID"));
        assertTrue(invoiceString.contains("Customer UUID"));
        assertTrue(invoiceString.contains("Salesperson UUID"));
        assertTrue(invoiceString.contains("Invoice Date"));
        assertTrue(invoiceString.contains("Subtotal"));
        assertTrue(invoiceString.contains("Tax Total"));
        assertTrue(invoiceString.contains("Grand Total"));
    }

    /**
     * Tests the subtotal, tax total, and grand total values with other types of invoice items.
     */
    @Test
    public void testInvoice02() {
        // Create sample items (Material, Contract)
        Material material = new Material(UUID.randomUUID(), "Steel Beam", "meters", 50.00, 10);
        Contract contract = new Contract(UUID.randomUUID(), "Construction Agreement", company, 20000.00);

        // Create InvoiceItem instances
        InvoiceItem item1 = new InvoiceItem(material);
        InvoiceItem item2 = new InvoiceItem(contract);

        // Create an invoice and add items
        Invoice invoice = new Invoice(UUID.randomUUID(), company, person, LocalDate.now());
        invoice.addItem(item1);
        invoice.addItem(item2);

        // Expected values
        double expectedSubtotal = material.calculateTotal() + contract.calculateTotal();
        double expectedTaxTotal = material.calculateTax(); // Assuming contract has no tax
        double expectedGrandTotal = expectedSubtotal + expectedTaxTotal;

        // Actual values from invoice methods
        double actualSubtotal = invoice.calculateSubtotal();
        double actualTaxTotal = invoice.calculateTaxTotal();
        double actualGrandTotal = invoice.calculateGrandTotal();

        // Assert results
        assertEquals(expectedSubtotal, actualSubtotal, TOLERANCE);
        assertEquals(expectedTaxTotal, actualTaxTotal, TOLERANCE);
        assertEquals(expectedGrandTotal, actualGrandTotal, TOLERANCE);
        
        String invoiceString = invoice.toString();
        
        assertTrue(invoiceString.contains("Invoice UUID"));
        assertTrue(invoiceString.contains("Customer UUID"));
        assertTrue(invoiceString.contains("Salesperson UUID"));
        assertTrue(invoiceString.contains("Invoice Date"));
        assertTrue(invoiceString.contains("Subtotal"));
        assertTrue(invoiceString.contains("Tax Total"));
        assertTrue(invoiceString.contains("Grand Total"));
        
        assertTrue(invoiceString.contains("John Doe agency"));
        
    }
}
