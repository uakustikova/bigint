package bigint;

public class Main {
    public static void main(String[] args) {
        // BigInt a = new BigInt("123");
        // System.out.println(a.toString());
        // BigInt b = BigInt.valueOf(12345);
        // System.out.println(b.toString());
        // BigInt c = new BigInt("-4563");
        // System.out.println(c.toString());
        // BigInt d = BigInt.valueOf(-12345);
        
        // assert d.toString().equals("-12345");
        // BigInt s = BigInt.valueOf(12345).add(BigInt.valueOf(-12345));
        // assert s.toString().equals("0"): s.toString();
        
        // BigInt s2 = BigInt.valueOf(12345).add(BigInt.valueOf(-22345));
        // assert s2.toString().equals("-10000") : s2.toString();
        // System.out.println(s2.toString());

        // BigInt s3 = BigInt.valueOf(22345).add(BigInt.valueOf(-12345));
        // assert s3.toString().equals("10000") : s3.toString();
        // System.out.println(s3.toString());

        // BigInt s4 = BigInt.valueOf(100).add(BigInt.valueOf(12345));
        // assert s4.toString().equals("12445");

        // BigInt s5 = BigInt.valueOf(1).add(BigInt.valueOf(99999));
        // System.out.println("s5: " + s5);
        // assert s5.toString().equals("100000");
        
        // BigInt s6 = BigInt.valueOf(0).add(BigInt.valueOf(-999));
        // System.out.println("s6: " + s6);
        // assert s6.toString().equals("-999");

        // BigInt s7 = BigInt.valueOf(-999).add(BigInt.valueOf(-999));
        // assert s7.toString().equals("-1998"): s7.toString();
        
        // BigInt m1 = BigInt.valueOf(999).subtract(BigInt.valueOf(10));
        // assert m1.toString().equals("989") : m1.toString();
        
       // BigInt m2 = BigInt.valueOf(999).multiply(BigInt.valueOf(10));
        //assert m2.toString().equals("9990") : m2.toString();
        //System.out.println(m2);

        //BigInt m3 = BigInt.valueOf(-38).multiply(BigInt.valueOf(567));
       // System.out.println("m3:" + m3);
        // BigInt m4 = BigInt.valueOf(111).subtract(BigInt.valueOf(3));
        // System.out.println("m4:" + m4);

//        final BigInt a = BigInt.valueOf(123).multiply(BigInt.valueOf(44)).add(BigInt.valueOf(3));
// final BigInt b = BigInt.valueOf(777).divide(BigInt.valueOf(7)).subtract(BigInt.valueOf(3));
// final int c = a.compareTo(b);
// System.out.println(a + (c == 0 ? " == " : (c == -1 ? " < " : " > ")) + b);
                
        // final BigInt firstBigInt = new BigInt("-10145353024723875673301314599046956334");
        // final BigInt secondBigInt = new BigInt("294719116278865064657958677920");
        // final BigInt res = firstBigInt.add(secondBigInt);

        // // Expected: res.toString() == "-10145352730004759394436249941088278414" 
        // // Got: res.toString() ==     "-101453527300047593944362499411088278414"
        // System.out.println("required: " + "-10145352730004759394436249941088278414");
        // System.out.println("actual:   " + res.toString());

        final BigInt firstBigInt = new BigInt("123123");
        final BigInt secondBigInt = new BigInt("5");
        final BigInt res = firstBigInt.divide(secondBigInt);
        System.out.println(res);
    }
}
