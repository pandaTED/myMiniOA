package cn.panda.oa.test;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by panda on 2015/12/20 0020.
 */
public class md5test {
    public static void main(String[] args) {

        String digest = DigestUtils.md5Hex("1234");
        System.out.println(digest);

    }
}
