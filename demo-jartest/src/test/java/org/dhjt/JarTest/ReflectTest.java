package org.dhjt.JarTest;

import static org.joor.Reflect.on;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.joor.ReflectException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Junit测试类
 * @author DHJT 2018年5月8日 上午9:24:09
 *
 */
public class ReflectTest {

	static final boolean JDK9 = false;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        Test1.S_INT1 = 0;
        Test1.S_INT2 = null;
        Test1.S_DATA = null;
    }

    @Test
    public void testOn() {
        assertEquals(on(Object.class), on("java.lang.Object", ClassLoader.getSystemClassLoader()));
        assertEquals(on(Object.class), on("java.lang.Object"));
        assertEquals(on(Object.class).<Object>get(), on("java.lang.Object").get());
        assertEquals(Object.class, on(Object.class).get());
        assertEquals("abc", on((Object) "abc").get());
        assertEquals(1, (int) (Integer) on(1).get());

        try {
            on("asdf");
            fail();
        }
        catch (ReflectException expected) {}

        try {
            on("asdf", ClassLoader.getSystemClassLoader());
            fail();
        }
        catch (ReflectException expected) {}
    }

    @Test
    public void testFields() throws Exception {
        // Instance methods
        // ----------------
        Test1 test1 = new Test1();
        assertEquals(1, (int) (Integer) on(test1).set("I_INT1", 1).get("I_INT1"));
        assertEquals(1, (int) (Integer) on(test1).field("I_INT1").get());
        assertEquals(1, (int) (Integer) on(test1).set("I_INT2", 1).get("I_INT2"));
        assertEquals(1, (int) (Integer) on(test1).field("I_INT2").get());
        assertNull(on(test1).set("I_INT2", null).get("I_INT2"));
        assertNull(on(test1).field("I_INT2").get());

        // Static methods
        // --------------
        assertEquals(1, (int) (Integer) on(Test1.class).set("S_INT1", 1).get("S_INT1"));
        assertEquals(1, (int) (Integer) on(Test1.class).field("S_INT1").get());
        assertEquals(1, (int) (Integer) on(Test1.class).set("S_INT2", 1).get("S_INT2"));
        assertEquals(1, (int) (Integer) on(Test1.class).field("S_INT2").get());
        assertNull(on(Test1.class).set("S_INT2", null).get("S_INT2"));
        assertNull(on(Test1.class).field("S_INT2").get());

        // Hierarchies
        // -----------
//        TestHierarchicalMethodsSubclass test2 = new TestHierarchicalMethodsSubclass();
//        assertEquals(1, (int) (Integer) on(test2).set("invisibleField1", 1).get("invisibleField1"));
//        assertEquals(1, accessible(TestHierarchicalMethodsBase.class.getDeclaredField("invisibleField1")).get(test2));
//
//        assertEquals(1, (int) (Integer) on(test2).set("invisibleField2", 1).get("invisibleField2"));
//        assertEquals(0, accessible(TestHierarchicalMethodsBase.class.getDeclaredField("invisibleField2")).get(test2));
//        assertEquals(1, accessible(TestHierarchicalMethodsSubclass.class.getDeclaredField("invisibleField2")).get(test2));
//
//        assertEquals(1, (int) (Integer) on(test2).set("invisibleField3", 1).get("invisibleField3"));
//        assertEquals(1, accessible(TestHierarchicalMethodsSubclass.class.getDeclaredField("invisibleField3")).get(test2));
//
//        assertEquals(1, (int) (Integer) on(test2).set("visibleField1", 1).get("visibleField1"));
//        assertEquals(1, accessible(TestHierarchicalMethodsBase.class.getDeclaredField("visibleField1")).get(test2));
//
//        assertEquals(1, (int) (Integer) on(test2).set("visibleField2", 1).get("visibleField2"));
//        assertEquals(0, accessible(TestHierarchicalMethodsBase.class.getDeclaredField("visibleField2")).get(test2));
//        assertEquals(1, accessible(TestHierarchicalMethodsSubclass.class.getDeclaredField("visibleField2")).get(test2));
//
//        assertEquals(1, (int) (Integer) on(test2).set("visibleField3", 1).get("visibleField3"));
//        assertEquals(1, accessible(TestHierarchicalMethodsSubclass.class.getDeclaredField("visibleField3")).get(test2));
//
//        assertNull(accessible(null));
    }
}
