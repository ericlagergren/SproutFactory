package utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * UUID is a simple, naive UUID implementation. It doesn't respect any versions. It doesn't respect anything or anybody.
 * It's a rebel, just like Oracle's terrible GUID implementation.
 */
public class UUID {
    public static void main(String... args) throws NoSuchAlgorithmException {
        UUID u = new UUID();
        System.out.println(u.toString());
    }

    public static final String NULL_UUID = "00000000000000000000000000000000";

    private byte[] data;
    private String str;

    public UUID() throws NoSuchAlgorithmException {
      this.data = new byte[16];
      SecureRandom.getInstanceStrong().nextBytes(this.data);
    }

    public UUID(byte[] p) {
      if (p.length != 16) {
          throw new IllegalArgumentException("p.length != 16");
      }
      this.data = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UUID uuid = (UUID) o;

        if (!Arrays.equals(data, uuid.data)) return false;
        return str != null ? str.equals(uuid.str) : uuid.str == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + (str != null ? str.hashCode() : 0);
        return result;
    }

    private final static char[] hexChars = "0123456789ABCDEF".toCharArray();

    @Override
    public String toString() {
      if (this.str != null)  { return this.str;  }
      if (this.data == null) { return NULL_UUID; }

      char[] out = new char[36];
      for (int i = 0; i < this.data.length; ++i) {
          int v = this.data[i] & 0xFF;
          out[i * 2]     = hexChars[v >>> 4];
          out[i * 2 + 1] = hexChars[v & 0x0F];
      }

      this.str = new String(out);
      return this.str;
    }
}
