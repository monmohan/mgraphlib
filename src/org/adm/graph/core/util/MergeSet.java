package org.adm.graph.core.util;

/**
 * Union Find data structure
 *
 * @param <E>
 */
public class MergeSet<E> {
    MergeSet<E> parent = null;
    E e = null;
    int rank = 0;

    public MergeSet(E e) {
        this.e = e;
        this.parent = this;

    }

    public MergeSet<E> find() {
        if (parent.e.equals(e)) {
            return parent;
        } else {
            return parent.find();
        }
    }

    public MergeSet<E> union(MergeSet<E> uf) {
        MergeSet<E> iRoot = find();
        int iRank = iRoot.rank;
        MergeSet<E> oRoot = uf.find();
        int oRank = oRoot.rank;
        if (iRank >= oRank) {
            oRoot.parent = iRoot;
            iRoot.rank++;
            return iRoot;
        } else {
            iRoot.parent = oRoot;
            oRoot.rank++;
            return oRoot;
        }


    }

}
