package DSS;

import java.math.BigInteger;

public class DSSUtils {
    public static BigInteger p = new BigInteger("23");
    public static BigInteger q = new BigInteger("11");
    public static BigInteger g = new BigInteger("4");
    public static BigInteger x = new BigInteger("3");
    public static BigInteger y = g.modPow(x,p);
    public static BigInteger customHash(String message){
        int sum = 0;
        for(char c : message.toCharArray()){
            sum+=c;
         }
         return BigInteger.valueOf(sum % q.intValue());
    }
    public static BigInteger[] sign(String message) {
        BigInteger k = new BigInteger("7"); // must be coprime with q
        BigInteger r = g.modPow(k, p).mod(q);
        BigInteger h = customHash(message);
        BigInteger kInv = k.modInverse(q);
        BigInteger s = kInv.multiply(h.add(x.multiply(r))).mod(q);
        return new BigInteger[]{r,s};
    }
    public static boolean verify(String message, BigInteger r, BigInteger s){
        BigInteger h = customHash(message);
        BigInteger w = s.modInverse(q);
        BigInteger u1 = h.multiply(w).mod(q);
        BigInteger u2 = r.multiply(w).mod(q);
        BigInteger v = g.modPow(u1,p).multiply(y.modPow(u2,p)).mod(p).mod(q);
        return v.equals(r);
    }
}
