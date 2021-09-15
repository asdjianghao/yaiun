package com.yaiun.controller.aspect;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaiun.common.annotation.Decrypt;
import com.yaiun.common.annotation.Encrypt;
import com.yaiun.common.util.encryption.AesUtil;
import com.yaiun.common.util.encryption.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * AES + RSA 加解密AOP处理
 */
@Aspect
@Component
public class SafetyAspect {

    /**
     * Pointcut 切入点
     * 匹配com.yaiun.controller包下面的所有方法
     */
    @Pointcut(value = "execution(* com.yaiun.controller.*.*(..))")
    public void safetyAspect() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "safetyAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            //request对象
            HttpServletRequest request = attributes.getRequest();

            //http请求方法  post get
            String httpMethod = request.getMethod().toLowerCase();

            //method方法
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();

            //method方法上面的注解
            Annotation[] annotations = method.getAnnotations();

            //方法的形参参数
            Object[] args = pjp.getArgs();

            //是否有@Decrypt
            boolean hasDecrypt = false;
            //是否有@Encrypt
            boolean hasEncrypt = false;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Decrypt.class) {
                    hasDecrypt = true;
                }
                if (annotation.annotationType() == Encrypt.class) {
                    hasEncrypt = true;
                }
            }

            //前端公钥
            String publicKey = null;

            //jackson
            ObjectMapper mapper = new ObjectMapper();
            //jackson 序列化和反序列化 date处理
            mapper.setDateFormat( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

            //执行方法之前解密，且只拦截post请求
            if ("post".equals(httpMethod) && hasDecrypt) {
                //AES加密后的数据
                //String data = request.getParameter("data");
                //后端RSA公钥加密后的AES的key
                //String aesKey = request.getParameter("aesKey");
                //前端公钥
                publicKey = request.getParameter("publicKey");

                System.out.println("前端公钥：" + publicKey);

                //后端私钥解密的到AES的key
                //byte[] plaintext = RsaUtil.decryptByPrivateKey(Base64.decodeBase64(aesKey), RsaUtil.getPrivateKey());
                //aesKey = new String(plaintext);
                //System.out.println("解密出来的AES的key：" + aesKey);

                //AES解密得到明文data数据
                //String decrypt = AesUtil.decrypt(data, aesKey);
                //System.out.println("解密出来的data数据：" + decrypt);

                //设置到方法的形参中
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                if(args.length > 0){
                    //args[0] = mapper.readValue(decrypt, args[0].getClass());
                }
            }

            Object o = pjp.proceed(args);

            //返回结果之前加密
            if (hasEncrypt) {
                //前端公钥
                publicKey = request.getParameter("publicKey");
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                //每次响应之前随机获取AES的key，加密data数据
                String key = AesUtil.getKey();
                System.out.println("AES的key：" + key);
                String dataString = mapper.writeValueAsString(o);
                System.out.println("需要加密的data数据：" + dataString);
                String data = AesUtil.encrypt(dataString, key);
                //用前端的公钥来加密AES的key，并转成Base64
                String aesKey = Base64.encodeBase64String(RsaUtil.encryptByPublicKey(key.getBytes(), publicKey));

                //转json字符串并转成Object对象，设置到Result中并赋值给返回值o
                Map<String,Object> map=new HashMap<>();
                map.put("data",data);
                map.put("aesKey",aesKey);
                map.put("key",key);
                o = JSON.parse(map.toString());
            }
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
