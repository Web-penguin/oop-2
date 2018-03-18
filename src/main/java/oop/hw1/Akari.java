package oop.hw1;


import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
В целом понятна идея переписать обфусцированный С код путем использования функционала Java.
Результат достигнут, программа делает то же самое, что и аналог программы на C.
Однобуквенные переменные, магические константы, все это приводит к плохой читаемости кода.
Так же проблематично модифицировать код, ибо непонятно, что вообще происходит.
*/

public class Akari {
    public static void main(String[] args) throws IOException {
//        /home/albert/HSE/oop/1/example.ppm /home/albert/HSE/oop/out.ppm
        int n = args.length;
		
		// Нет закрытия файловых дескрипторов p и q.
        InputStream p;
        OutputStream q;
        int A = 0, k, a, r = 0, i = 0;
        String d = "P%d\n%d %d\n%d\n";
        byte[] b = new byte[1024];
        char[] y = ("yuriyurarararayuruyuri*daijiken**akkari~n**" +
                "/y*u*k/riin<ty(uyr)g,aur,arr[a1r2a82*y2*/u*r{uyu}riOcyurhiyua**rrar+*arayra*=" +
                "yuruyurwiyuriyurara'rariayuruyuriyuriyu>rarararayuruy9uriyu3riyurar_aBrMaPrOaWy^?" +
                "*]/f]`;hvroai<dp/f*i*s/<ii(f)a{tpguat<cahfaurh(+uf)a;f}vivn+tf/g*`*w/jmaa+i`ni(" +
                "i+k[>+b+i>++b++>l[rb").toCharArray();

        int u;
        for (i = 0; i < 101; i++) {
            y[i * 2] ^= ("~hktrvg~dmG*eoa+%squ#l2" +
                    ":(wn\"1l))v?wM353{/Y;lgcGp`vedllwudvOK`cct~[|ju {stkjalor(stwvne\"gt\"yogYURUYURI").toCharArray()[i] ^ y[i * 2 + 1] ^ 4;
        }

        p = (n > 1 && ((args[0].charAt(0) - '-') != 0 || args[0].charAt(1) != '\0')) ? new FileInputStream(args[0]) : System.in;
        q = (n < 2 || !((args[1].charAt(0) - '-') != 0 || args[1].charAt(1) != '\0')) ? System.out : new FileOutputStream(args[1]);

        for (a = k = u = 0; u < y.length; u = 2 + u) {
            y[k++] = y[u];
        }

        a = p.read(b);

        Matcher m = Pattern.compile("P(\\d+)\\n(\\d+) (\\d+)\\n(\\d+)\\n").matcher(new String(b));

        if (m.find()) {
            k = Integer.parseInt(m.group(1));
            A = Integer.parseInt(m.group(2));
            i = Integer.parseInt(m.group(3));
            r = Integer.parseInt(m.group(4));
        }

        if (a > 2 && (4 == m.groupCount()) && (char)b[0] == 'P' && !((k - 6 != 0) && (k - 5 != 0)) && r == 255) {
            u = A;
            if (n > 2) {
                u++;
                i++;
            }

            q.write(String.format(d, k, u >> 1, i >> 1, r).getBytes());

            u = (k - 5) != 0 ? 8 : 4;
            k = 3;
        } else {
            u = (n + 15 > 17) ? 8 / 4 : 8 * 5 / 4;
        }

        for (r = i = 0; ;) {
            u *= 6;
            u += (n > 2 ? 1 : 0);
            if ((y[u] & 01) != 0)
                q.write((1 * r));
            if ((y[u] & 16) != 0)
                k = A;
            if ((y[u] & 2) != 0)
                k--;
            if (i == a) {
                i = (u * 11) & 255;
                a = (u * 11) & 255;
                if (0 >= (a = p.read(b)) && (int)(")]i>(w)-;} {                                         /i-f-(-m--M1-0.)<{".charAt(8)) == 59)
                    break;
                i = 0;
            }
            r = b[i++];
            u += ((8 & y[u]) != 0 ? ((10 - r) != 0 ? 4 : 2) : (y[u] & 4) != 0 ? (k != 0 ? 2 : 4) : 2);
            u = (y[u] - (int)'`');
        }
    }
}
