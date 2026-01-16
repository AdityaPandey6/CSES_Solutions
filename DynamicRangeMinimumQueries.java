class FastScanner {
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0, len = 0;

    private int readByte() {
        if (ptr >= len) {
            try {
                len = System.in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            } catch (Exception e) {
                return -1;
            }
        }
        return buffer[ptr++];
    }

    long nextLong() {
        int c;
        long sign = 1, res = 0;

        do {
            c = readByte();
        } while (c <= ' ');

        if (c == '-') {
            sign = -1;
            c = readByte();
        }

        while (c > ' ') {
            res = res * 10 + (c - '0');
            c = readByte();
        }

        return res * sign;
    }
}
class SegTree{
    long [] segTree ;

    SegTree(int n){
        segTree = new long[4*n];
    }

    void buildTree(int idx , int l , int r , long [] nums){
        if(l == r){
            segTree[idx] = nums[l];
            return ;
        }
        int mid = l + (r-l)/2;
        buildTree(2*idx+1, l, mid, nums);
        buildTree(2*idx+2, mid+1, r, nums);
        segTree[idx] = Math.min(segTree[2*idx+1] , segTree[2*idx+2]);
    }
 void pointUpdate(int idx, int l, int r, int pos, long val){
    if(l == r){
        segTree[idx] = val;
        return;
    }
    int mid = l + (r - l) / 2;
    if(pos <= mid)
        pointUpdate(2*idx+1, l, mid, pos, val);
    else
        pointUpdate(2*idx+2, mid+1, r, pos, val);

    segTree[idx] = Math.min(segTree[2*idx+1] , segTree[2*idx+2]);
}


    long query(int idx , int start , int end , int l , int r ){
        if(r < start || l > end){
            return Long.MAX_VALUE;
        }

        if(start <= l && r<=end){
            return segTree[idx];
        }
        int mid = l + (r-l)/2;
        return Math.min(query(2*idx + 1 , start , end , l , mid) , query(2*idx+2, start, end, mid+1, r));
    }
}
public class DynamicRangeMinimumQueries {
    public static void main(String[] args){
        FastScanner fs = new FastScanner();
        int n = (int) fs.nextLong();
        int q = (int) fs.nextLong();
        long []arr = new long[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = fs.nextLong();
        }
        SegTree st = new SegTree(n);
        st.buildTree(0, 0, n-1, arr);
        StringBuilder ans = new StringBuilder();
        while(q-->0){
            int type = (int) fs.nextLong();
            int l = (int) fs.nextLong();
            int r = (int) fs.nextLong();
            if(type == 1){
                st.pointUpdate(0, 0, n-1, l-1, r);
            }
            else{
                ans.append(st.query(0, l-1, r-1, 0, n-1)).append('\n');
            }
        }
        System.out.println(ans);
    }
}