package primedirective;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrimeFactorSequence {
    private List<Integer> primes;
    private int upperBound;
    /**
     * Create a PrimeFactorSequence object with a defined upperbound.
     *
     * @param _upperBound the upper bound for sequences and primes in this instance,
     * {@code upperBound > 0}.
     */
    public PrimeFactorSequence(int _upperBound) {
        upperBound = _upperBound;
        primes = Primes.getPrimes(upperBound);
    }

    /**
     * Obtain a sequence L[0 .. upper bound] where L[i] represents the number of prime factors i
     * has, including repeated factors
     *
     * @return sequence L[0 .. upper bound] where L[i] represents the number of prime factors i
     * has, including repeated factors
     */
    public List<Integer> primeFactorSequence() {
        List<Integer> seq = new ArrayList<>();
        for(int i = 0; i < upperBound + 1; i++){
            seq.add(primeFactorCount(i));
        }

        return seq;
    }

    public int primeFactorCount(int n){
        if(n == 0 || n == 1){
            return 0;
        }

        if(primes.contains(n)){
            return 1;
        }

        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return 1 + primeFactorCount(n / i);
            }
        }

        return 0;
    }

    /**
     * Obtain a sequence L that is sorted in ascending order and where L[i] has exactly m
     * prime factors (including repeated factors) and L[i] <= upper bound
     *
     * @param m the number of prime factors that each element of the output sequence has
     * @return a sequence L that is sorted in ascending order and where L[i] has exactly
     * m prime factors (including repeated factors) and L[i] <= upper bound
     */
    public List<Integer> numbersWithMPrimeFactors(int m) {
        List<Integer> seq = new ArrayList<>();
        // TODO: Implement this method
        for(int i = 0; i< upperBound + 1; i++){
            if(primeFactorCount(i) == m){
                seq.add(i);
            }
        }
        return seq;
    }

    /**
     * Obtain a sequence of integer pairs [(Sa, Sb)] where Sa and Sb have exactly m prime factors
     * (including repeated factors), and where |Sa - Sb| <= gap and where Sa and Sb are
     * adjacent each other in the output of {@code numbersWithMPrimeFactors(m)}
     *
     * @param m   the number of prime factors that each element in the output sequence has
     * @param gap the maximum gap between two adjacent entries of interest in the output
     *            of {@code numbersWithMPrimeFactors(m)}
     * @return a sequence of integer pairs [(Sa, Sb)] where Sa and Sb have exactly
     * m prime factors (including repeated factors), and where |Sa - Sb| <= gap and where
     * Sa and Sb are adjacent each other in the output of {@code numbersWithMPrimeFactors(m)}
     */
    public List<IntPair> numbersWithMPrimeFactorsAndSmallGap(int m, int gap) {
        List<IntPair> listOfPairs = new ArrayList<>();
        // TODO: Implement this method
        List<Integer> listofMfactors = numbersWithMPrimeFactors(m);

        for(int i = 0; i < listofMfactors.size() - 1; i++){
            if(listofMfactors.get(i+1) - listofMfactors.get(i) <= gap){
                listOfPairs.add(new IntPair(listofMfactors.get(i), listofMfactors.get(i+1)));
            }
        }

        return listOfPairs;
    }

    /**
     * Transform n to a larger prime (unless n is already prime) using the following steps:
     * <p>
     *     <ul>
     *         <li>A 0-step where we obtain 2 * n + 1</li>,
     *         <li>or a 1-step where we obtain n + 1</li>.
     *     </ul>
     *      We can apply these steps repeatedly, with more details in the problem statement.
     * </p>
     * @param n the number to transform
     * @return a string representation of the smallest transformation sequence
     */
    public String changeToPrime(int n) {
        // TODO: Implement this method
        shortestLength = Integer.MAX_VALUE;
        curr = "";
        changeOperation("", n);

        if(shortestLength == Integer.MAX_VALUE && curr.equals("")){
            return "-";
        }

        return curr;
    }

    private int shortestLength;
    private String curr;
    public void changeOperation(String target, int n){

        if(primes.contains(n)){
            System.out.println(target);
            System.out.println(curr.length());
            if(target.length() > shortestLength){
                return;
            } else {
                if(shortestLength == Integer.MAX_VALUE && curr.equals("")){
                    curr = target;
                    shortestLength = target.length();
                    return;
                } else if(shortestLength == target.length()){
                    for(int i = 0; i < target.length(); i++){
                        if(Integer.parseInt(String.valueOf(target.charAt(i)))< Integer.parseInt(String.valueOf(curr.charAt(i)))){
                            curr = target;
                            shortestLength = target.length();
                            return;
                        }
                        return;
                    }
                } else {
                    curr = target;
                    shortestLength = target.length();
                    return;
                }
            }
            return;
        } else if(n == upperBound || 2*n + 1 >= upperBound){
            if(shortestLength == Integer.MAX_VALUE){
                return;
            }
        } else {
            if(target.length() > shortestLength){
                return;
            }
            changeOperation(target + '0', 2*n + 1);
            changeOperation(target + '1', n + 1);
        }
    }

}
