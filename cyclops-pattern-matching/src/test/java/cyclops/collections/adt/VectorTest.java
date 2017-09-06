package cyclops.collections.adt;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.*;


public class VectorTest {

    @Test
    public void testVector(){
        Vector<Integer> ints = Vector.<Integer>empty().plus(1);
        assertThat(ints.get(0),equalTo(Optional.of(1)));
    }
    @Test
    public void testVector100(){
        Vector<Integer> ints = Vector.<Integer>empty();
        for(int i=0;i<1025;i++){
            ints = ints.plus(i);
        }

        assertThat(ints.get(0),equalTo(Optional.of(0)));
        assertThat(ints.get(900),equalTo(Optional.of(900)));
    }

    @Test
    public void last(){
        Object[] array = {"hello","world"};
        assertThat(BAMT.ArrayUtils.last(array),equalTo("world"));
    }
    @Test
    public void test3Pow(){
        Vector<Integer> ints = Vector.<Integer>empty();

        int p  = Double.valueOf(Math.pow(2,15)).intValue();
        for(int i=0;i<p;i++){
            System.out.println(i);
            ints = ints.plus(i);
        }
        for(int i=0;i<p;i++){
            assertThat(ints.get(i),equalTo(Optional.of(i)));
        }


    }

    @Test
    public void test3PowSet(){
        Vector<Integer> ints = Vector.<Integer>empty();

        int p  = Double.valueOf(Math.pow(2,15)).intValue();
        for(int i=0;i<p;i++){

            ints = ints.plus(i);
        }
        for(int i=0;i<p;i++){
            System.out.println(i);
            ints = ints.set(i,i*2);
        }
        for(int i=0;i<p;i++){
            assertThat(ints.get(i),equalTo(Optional.of(i*2)));
        }

    }
    @Test
    public void test4Pow(){
        Vector<Integer> ints = Vector.<Integer>empty();

        int p  = Double.valueOf(Math.pow(2,20)).intValue();
        for(int i=0;i<p;i++){
            ints = ints.plus(i);
        }
        for(int i=0;i<p;i++){
            assertThat(ints.get(i),equalTo(Optional.of(i)));
        }


    }

    @Test
    public void test4PowSet(){
        Vector<Integer> ints = Vector.<Integer>empty();

        int p  = Double.valueOf(Math.pow(2,20)).intValue();
        for(int i=0;i<p;i++){


            ints = ints.plus(i);
        }
        for(int i=0;i<p;i++){
            ints = ints.set(i,i*2);
        }
        for(int i=0;i<p;i++){
            assertThat(ints.get(i),equalTo(Optional.of(i*2)));
        }

    }
    @Test
    public void test5Pow(){
        Vector<Integer> ints = Vector.<Integer>empty();

        int p  = Double.valueOf(Math.pow(2,25)).intValue();
        for(int i=0;i<p;i++){
            ints = ints.plus(i);
        }
        for(int i=0;i<p;i++){
            assertThat(ints.get(i),equalTo(Optional.of(i)));
        }


    }

    @Test
    public void test5PowSet(){
        Vector<Integer> ints = Vector.<Integer>empty();

        int p  = Double.valueOf(Math.pow(2,25)).intValue();
        for(int i=0;i<p;i++){


            ints = ints.plus(i);
        }
        for(int i=0;i<p;i++){
            ints = ints.set(i,i*2);
        }
        for(int i=0;i<p;i++){
            assertThat(ints.get(i),equalTo(Optional.of(i*2)));
        }

    }


}