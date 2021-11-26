package model;
import model.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTypeTest {
    @Test
    public void testBoardType(){
        assertEquals("STANDARD", BoardType.STANDARD.name().toString());
        assertEquals("TURKISH", BoardType.TURKISH.name().toString());
        assertEquals("EMPTY", BoardType.EMPTY.name().toString());
    }
}