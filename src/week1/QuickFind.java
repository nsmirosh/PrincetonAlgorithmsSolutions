package week1;

public class QuickFind {


    private final int[] id;

    public QuickFind(int n) {
        id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }


    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {

        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

}


/*

package quick_find

class QuickFind(n: Int) {

    private var ids = Array(10) { i -> i }

    init {
        for (i in 0 until n) {
            ids[i] = i
        }
    }

    fun connected(q: Int, p: Int) = ids[q] == ids[p]


    fun union(q: Int, p: Int) {


        println("============")
        println()

        println("status before the union operation: ")
        print()
        println("performing union of positions $q and $p")
        val qid = ids[q]
        val pid = ids[p]
        println("qid = $qid, pid = $pid")

        for (i in ids.indices) {
            if (ids[i] == pid)  {
                println("match found for ids[$i] and pid = $pid")
                ids[i] = qid
                println("setting value for ids[$i] to $qid")
            }
        }

        print()
    }


    fun print() {
        print(" ")

        ids.forEachIndexed { i, item->
            print("$i  ")
        }
        println()
        print("[")
        ids.forEach {
            print("$it, ")
        }
        println("]")
    }

}
 */
