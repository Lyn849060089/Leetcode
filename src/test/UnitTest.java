package test;

import org.junit.Test;
import utils.UnionFind;

public class UnitTest {

    @Test
    public void testUnionFind() {
        UnionFind uf = new UnionFind(4);
        System.out.println(uf);

        uf.merge(0, 1);
        System.out.println(uf);

        uf.merge(0, 2);
        System.out.println(uf);
        System.out.println(uf.getCount(3));
    }
}
