package com.cmcc.vr.mid.probe.aka.cipher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

/**
 * CipherUtil
 *
 * @Type CipherUtil.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:46
 */
public class CipherUtil {

    /**
     * GetToSignStr
     *
     * @param map
     * @return
     */
    public static String getToSignStr(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            sb.append(map.get(key));
        }
        return sb.toString();
    }

    /**
     * ReadKeyFile
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static byte[] readKeyFile(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuffer key = new StringBuffer();
        String s = br.readLine();
        while (s.charAt(0) != '-') {
            key.append(s);
            s = br.readLine();
        }
        br.close();
        byte[] keyByte = Base64Util.decode(key.toString());
        return keyByte;
    }

    /**
     * ReadKeyFile
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readKeyFile(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer key = new StringBuffer();
        String s = bufferedReader.readLine();
        while (s != null) {
            if (!s.isEmpty() && s.charAt(0) != '-') {
                key.append(s);
            }
            s = bufferedReader.readLine();
        }
        bufferedReader.close();
        byte[] keyByte = Base64Util.decode(key.toString());
        return keyByte;
    }

    /**
     * getPrivateKey
     *
     * @return
     */
    public static byte[] getPrivateKey(){
        String privateKey0 =
                "MIIEpgIBAAKCAQEA4wTZFKbty2DXkuNwbiD5tFpTx9wucxf8B0LoOZOCyr8T0mrZ" +
                "XEp3KpW/Tuk6zDyz56Mzy+CTP3UdwW9vnWrMVsBEIUXnxaNX7IKAL5ONZg7tIbmZ" +
                "X1ksCYDZxaHgNvhwBTL50T3OxeudYh/kLrFSkT7svIcUdev5bYs2J0NoSqfTJj/b" +
                "pQpW83iK4qTyegkMywhf3JUieorQqot4hQld5UVR7HKsy8+wSA8EDGJLQ1l2/Srf" +
                "ZhZCuW5Og8x86/R1L0840ZgJXqGONFdwKQRWK7SXyqTUHQCfTysudU3CV5z3/mr5" +
                "FkeSRk1vK5s/DpzPBTv8RYco7OGAh8M3QGgGNwIDAQABAoIBAQCzvTLiVwgO7i2/" +
                "7rMlfpY1ZQ7QPlPIbETuutqCO3hibcaT4mUV3R9caBN/tskGy0jlLSHlWXk71M4q" +
                "SMBmMhh21uMaNO68XAdRCkHTaU+ablbxTLFR/JgRLiM9hlyvKSQH+cQxFm6P5WxG" +
                "NFnFXj+xH9Dvs0NP2fUx9fZ6P7Ri5d87as+hLEJMShtLJKQcV4GIFjgcNr8l6d7h" +
                "XNvDzzyodK841CcDqt88kp/ZOjsrh1GJ6ypepv6C2uwWEvow0pulLyaUJf4ywIBt" +
                "S6ELzYHr5yj2ODNGl4jXa5O16RHFctTnf2Pb21xo9+S+ldCuXzZ3+QiYfVAx2Yy/" +
                "nlQoTImBAoGBAPctR8fywHXrVCt2K3eFqNJ3K3r/5sNzJRTydCQBt9XnVNGb9BsS" +
                "omVJJVeiUAqSznC71piVW854HYBF0sojltCBZIjoY//zgwZmqiyIoXngpeaKw1+K" +
                "qZKzkwT52xCcL8nl6BNE++AMRkIsLjHB3t1Pgape1aiEIuL7amhWEyU1AoGBAOsf" +
                "XOgkTBJ8Cuq5waJrW+WMgMVab9/LmIvnyp2HSkQ5X8SHvDi4YNNx6wh6XKuz7Ng6" +
                "Pa3IWqLH3EvokVbg7oOk8X8wbGNJf1+OJnGwu9YW6aVXtvBu7AKqpmUnKCNBDEVA" +
                "v8hljCYWkCWLpiDvW3upBWNrC5t9TLaG2G81Ugc7AoGBAJSTsUwtTScUl/mwBJzJ" +
                "yRsJ6yb6X9oogqe+vbVPGxQHA+MjXW5QmzFK8Jlmxbo1WMZHAs2hKhhH92O9lOFQ" +
                "1zzqKmZpWoLl+2KDn9M5Jec+9DpKOvpZPWkjbEJXdijb2ZYBDqlWlBivCCSKrhHW" +
                "uYJKYmTosIGlO9s4yVOCOevpAoGBAOSZprQR7mCed+LF3m0zIrMpi13WUyVtBxEn" +
                "uUi17FAu1Fz5d9hR89pBTTn99jH/Cs0kuylGvlC8LwUixoSIx335FdAK3acfI5dd" +
                "VyV8pvQ9bMeUY8qanh7tmkCI9K5qJSsEDDMkAEJb96R5hXlKH1E5AYMq5as1BSwG" +
                "KVLerbVXAoGBAKuk+gGZxP4uAO+I9073If4jIYnp0UNNa/jjnnYOQx1s2gBDWeuH" +
                "u2dbTTJppW2xZb+4FxWcUykE1Z/6cGqTS99Cvm16B+3BlJ83wap7j9sfCAsI0NI+" +
                "OmKgNbSFLQLIhKysFlJrLSI3wA2YCGd993ngsuwTFsV8H1Lqsw0cue54";

        String privateKey1 =
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCmywg6mZhNrarr" +
                "vbFnWNCWtSSGiQ2PJlEb6Ds9uaiH3K71yxm2qAAEVWNeUlkSJ8f7eVJV6FqCycQe" +
                "YBjqsk3rUSr/DuWPyZSownmC/whECSVyhArGcKglsTRkboNcrlqtTe00q/rkcg/H" +
                "Y5Ss9SDarIcPltgtSchqp4zEwzKt/SikvbkP5Vw7X8s52zeJfrMvWSIH4tbWhe/Z" +
                "YJ6M54bbdURjHxp8Vp6v5zExyMC0DhKRHFFsnYhCGxoNl2WWHc1OY+MF9+q/SD2b" +
                "3o/HWdhtaHomQmtOhcFZxql42efQRwKtYE7lknoxkFEmbbrR/ru0hSJI4Z66WRCG" +
                "BjyaIwPLAgMBAAECggEAMF2XmUaIv52ofouXMdyzP8BvQwPjBquZwXnqE9AOarc3" +
                "lNa8MhctShRoqCVA5t7aQrW/hmHGPT68vAIpt4TUy3PR//wV3Uga74TVL4M8pl3w" +
                "mfBoH3rzh1qHUsgpF2sHqFF8g1WLf0sEAFGD/1Nln4N/9qlLLnbCwwqq6zOfMoxK" +
                "lHid/kDupt0V63MHVUI0n/2bb5lAqNUj1mZn3rZu53hutgao1rAy4BG/sk/TS58y" +
                "jV/mep8pW6HyCYKF6V8Z+gPBDFAnzzEE3EkDFnI5MOqH07YRt2hUzFJd0cqliCJm" +
                "roDyGIjyog9zsMAmJIrG75fSqyvXlV6uLdj6EUVGIQKBgQDX2UsybyMJ62A9xBYu" +
                "j7TjHmOMYORGAGOmO+wv+O9yjz3Fj1M2PMA3BQ7PXttX3lazC5qvTkxPMihY4ayO" +
                "sYeyi3d7abjlfWVKacRbJvCjyf9955qLpZSVJ8C5JlWDOXT2VitRc+6OrAxXhQcD" +
                "HJqXYeWBCVMrIv5Ke7BO+XZh7wKBgQDF0bVqdlA8+jgsaKnJVeiAPEBh6HQU+/1e" +
                "T/6BJxf026nOfN00Fd4oFNNR+z0U1ot7lkjzLdBHvB12LFdCtwqOKiQGZ17PYf1n" +
                "8YZbpT4oXU/CBT6Q+GR69tEM4N7tF5pLetQ1FePIdPH7PocCOFDAOrdO38WXTLIS" +
                "HdpSJM4n5QKBgFkcLB82IpEFy0W0njO6wELDWPWrSpJbtsf6FRf4/WGeITJeaUwK" +
                "6I/OVr7lukgcUk64i7Oz720CmAWZpVODlzHuwfUBQIP9sMN3xsia2t3Dluazpung" +
                "AedR8g599O+n2NGZ4WhPJD1iafX3xV1Tgb7de5I590vntnOpfLAd7PGhAoGBAJpj" +
                "DL9GD2FHt8eQ99HWZlqOq4C+NrqZ4ChXLj4GEMBdH62z7b/UwUlu125IMmes+bc+" +
                "lziTLNaPjj7IZGIBEQ/a+7qzul8IDwmsijvEPzqpeLHn/eiOs3z0XDs81T8dCKKX" +
                "/j5gEyJDgweeySeGZB9is+Wu5zsp9GAHRHiPmQWxAoGABhG2gUZnx8lNpfkzG88K" +
                "Wukk/hHQlDCZObPBUSOFlcW/bJbe48rOjq51B2K9bTthQwbLiY3dwkj1u1PzNuRH" +
                "yJEVY+IgCPueJBOKwDvFUACtvAZOlvwlv/ajvmuEsz0LGc6URtUZpyE6Mrrh7kiA" +
                "FPWNURF6X3WG/mA9zaUnftE=";
        byte[] keyByte = Base64Util.decode(privateKey1);
        return keyByte;
    }
}
