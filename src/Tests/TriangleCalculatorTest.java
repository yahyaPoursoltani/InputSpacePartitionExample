import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TriangleCalculatorTest {
    private TriangleCalculator calculator;
    @BeforeEach
    public void setUp(){
        this.calculator = new TriangleCalculator();
    }

    @DisplayName("Detecting Negative or Zero Values for sides.")
    @ParameterizedTest(name = "Test {index} : Invalid Side Sizes with values a = {0} , b = {1} , c= {2}")
    @MethodSource("generateExceptionalSideValue")
    public void testANonNeg(double side1, double side2, double side3){
        if(side1<=0 || side2<=0 ||side3<=0){
            Assertions.assertThrows(IllegalArgumentException.class,() -> calculator.calculateTriangleArea(side1,side2,side3));
        }
    }


    public static Stream<Arguments> generateExceptionalSideValue(){
        return Stream.of(
                Arguments.of(0,1,1),
                Arguments.of(1,0,1),
                Arguments.of(1,1,0)
        );
    }


    @DisplayName("Detecting Invalid Triangle ")
    @ParameterizedTest(name = "Test {index} : Invalid Triangle = {0} , b = {1} , c= {2}")
    @MethodSource("generateInvalidTriangle")
    public void testInvalidTriangle(double side1, double side2, double side3){
        boolean canBeTriangle = (side3 < side1+side2) && (side2 < side1+side3) && (side1 < side2+side3);
        if(!canBeTriangle){
            Assertions.assertThrows(IllegalArgumentException.class,() -> calculator.calculateTriangleArea(side1,side2,side3));
        }
    }


    public static Stream<Arguments> generateInvalidTriangle(){
        return Stream.of(
                Arguments.of(1,2,3)
        );
    }

    @DisplayName("Test for correct computation of area of Valid Triangles.")
    @ParameterizedTest(name = "Test {index} : Valid Triangle = {0} , b = {1} , c= {2}")
    @MethodSource("generateValidTriangle")
    public void testValidTriangle(double side1, double side2, double side3){
        boolean canBeTriangle = (side3 < side1+side2) && (side2 < side1+side3) && (side1 < side2+side3);
        if(canBeTriangle && 0<side1 && 0<side2 && 0<side3){
            //calculate Area
            double s = (side1 + side2 + side3) / 2;
            double computedArea = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
            Assertions.assertEquals(computedArea,calculator.calculateTriangleArea(side1,side2,side3));
        }
    }


    public static Stream<Arguments> generateValidTriangle(){
        return Stream.of(
                Arguments.of(3,4,5)
        );
    }



}
