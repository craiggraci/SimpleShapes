/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package math;

/**
 *
 * @author blue
 */
public class Predicates {

            static public boolean odd(int i)
            {
               return ! ( i == ( ( i / 2 ) * 2 ) );
            }

            static public boolean divides(int i, int n)
            {
               return ( n == ( ( n / i ) * i ) );
            }

            static public boolean even(int i)
            {
               return ! odd(i);
            }

            static public boolean congruent(int c, int m, int x)
            {
               if ( x < 0 )
               {
                  return false;
               }
               else if ( x == c )
               {
                  return true;
               }
               else
               {
                  return congruent(c,m,x-m);
               }
            }

            static public boolean multiple(int m, int x)
            {
               if ( x < 0 )
               {
                  return false;
               }
               else if ( x == 0 )
               {
                  return true;
               }
               else
               {
                  return multiple(m,x-m);
               }
            }

}
