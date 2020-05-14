package com.cmcc.vr.mid.probe.aka;

import android.content.Context;

import com.cmcc.vr.mid.probe.aka.cipher.Base64Util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * CrtVerifyHelper
 *
 * @Type CrtVerifyHelper.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:45
 */
public class CrtVerifyHelper {
    private static final String TAG = "CrtVerifyHelper";
    private BouncyCastleProvider mBouncyCastleProvider;
    private String caCrt = "ca.crt";

    private X509Certificate rootCertificate;

    private static CrtVerifyHelper cipher = new CrtVerifyHelper();

    private CrtVerifyHelper() {
        mBouncyCastleProvider = new BouncyCastleProvider();
        Security.addProvider(mBouncyCastleProvider);
    }

    /**
     * getInstance
     *
     * @return
     */
    public static CrtVerifyHelper getInstance() {
        return cipher;
    }

    /**
     * checkServerCertificateValid
     *
     * @return
     */
    public boolean checkServerCertificateValid(Context context, X509Certificate serverCertificate) {
        try {
            synchronized (this) {
                if (rootCertificate == null) {
                    rootCertificate = getRootCertificate(context);
                }
            }
            if (rootCertificate != null) {
                serverCertificate.verify(rootCertificate.getPublicKey());
                KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                keystore.load(null, null);
                keystore.setCertificateEntry("ca", rootCertificate);
                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keystore);
                TrustManager[] trustManagers = tmf.getTrustManagers();
                X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
                trustManager.checkServerTrusted(new X509Certificate[]{serverCertificate}, "RSA");
                String name = serverCertificate.getSubjectDN().getName();
                String[] split = name.split(",");
                for (String e : split) {
                    if (e.startsWith("CN=")) {
                        String domain = e.trim().substring(3);
                        //ConfigUtils.getInstance(context).getStbServerAddress();
                        if (!"localhost".equals(domain)) {
                            throw new Exception("domain is wrong: " + domain);
                        }
                    }
                }
                return true;
            }
        } catch (Exception e) {
            System.err.println("check fail.");
            e.printStackTrace();
        }
        return false;
    }

    private X509Certificate getRootCertificate(Context context) throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509", mBouncyCastleProvider);
        InputStream is = null;
        try {
            is = context.getAssets().open(caCrt);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
            return cert;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignore) {
                }
            }
        }
    }

    /**
     * getServerCertificate
     *
     * @return
     */
    public X509Certificate getServerCertificate(String certificateStr) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509", mBouncyCastleProvider);
        InputStream is = new ByteArrayInputStream(Base64Util.decode(certificateStr.getBytes()));
        try {
            X509Certificate svrCert = (X509Certificate) cf.generateCertificate(is);
            return svrCert;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignore) {
                }
            }
        }
    }
}
