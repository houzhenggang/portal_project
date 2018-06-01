package com.qdch.portal.common.filter;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.utils.AESUtil;
import net.sf.ehcache.constructs.web.filter.Filter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class BusinessRequestFilter extends Filter {
    @Override
    protected void doDestroy() {

    }

    @Override
    protected void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws Throwable {

//        String a  = AESUtil.encrypt("xusong",getItemID(16));
//        System.out.println(a);
//        a = AESUtil.decrypt(a,"1234567812345678");
//        System.out.println(a);
       // String token = httpServletRequest.getParameter("token");
        //token = AESUtil.decrypt(token,Global.getSaltkey()); //得到userid,時間戳
//        String oldtime =
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doInit(FilterConfig filterConfig) throws Exception {

    }

    private static String getItemID( int n )
    {
        String val = "";
        Random random = new Random();
        for ( int i = 0; i < n; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        return val;
    }
}
