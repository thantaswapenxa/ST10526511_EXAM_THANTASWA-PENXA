import com.mycompany.productsalesgui.ProductSales;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductSalesTest {

    // Create your ProductSales object here once
    private final ProductSales ps = new ProductSales();
    
    // Test data for Question 1
    private final int[][] testDataQ1 = {{300, 150, 700}, {250, 200, 600}};

    // ========== QUESTION 1 TESTS ==========
    @Test
    void CalculateTotalSales_ReturnsTotalSales() {
        assertEquals(2200, ps.TotalSales(testDataQ1));
    }

    @Test
    void AverageSales_ReturnsAverageProductSales() {
        assertEquals(366.667, ps.AverageSales(testDataQ1), 0.001);
    }

    @Test
    void MaxSale_ReturnsMaximum() {
        assertEquals(700, ps.MaxSale(testDataQ1));
    }

    @Test
    void MinSale_ReturnsMinimum() {
        assertEquals(150, ps.MinSale(testDataQ1));
    }

    // ========== QUESTION 2 TESTS ==========
    @Test
    void GetSalesOverLimit_ReturnsNumberOfSales() {
        assertEquals(2, ps.GetSalesOverLimit());
    }

    @Test
    void GetSalesUnderLimit_ReturnsNumberOfSales() {
        assertEquals(4, ps.GetSalesUnderLimit());
    }
}