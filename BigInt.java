package bigint;

import java.util.ArrayList;
import java.util.List;

final class BigInt {
    private final ArrayList<Integer> digits;
    private boolean sign;

    public BigInt(String number) {
        ArrayList<Integer> deepcopy = new ArrayList<Integer>();
        this.sign = true;
        if (number.charAt(0) == '-') {
            this.sign = false;
            number = number.substring(1);
        }
        for (int i = 0; i < number.length(); i++) {
            deepcopy.add(Integer.valueOf(Character.toString(number.charAt(i))));
        }

        this.digits = deepcopy;
    }

    public BigInt(List<Integer> digits, boolean sign) {
        this.sign = sign;
        this.digits = new ArrayList<Integer>(digits);
    }

    public BigInt() {
        this.sign = true;
        this.digits = new ArrayList<Integer>();
    }

    @Override
    public String toString() {
        String answer = "";
        if (!this.sign) {
            answer += "-";
        }
        for (int c : this.digits) {
            answer += Integer.toString(c);
        }
        return answer;
    }

    public static BigInt valueOf(long number) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        boolean sign = number >= 0;
        if (number < 0) {
            number = number * (-1);
        }
        if (number == 0) {
            digits.add(0);
        } else
            while (number > 0) {
                digits.add(0, Integer.valueOf((int) (number % 10)));
                number = (number - number % 10) / 10;
            }
        return new BigInt(digits, sign);

    }

    public BigInt add(BigInt other) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int addon = 0, summ;
        int mult = other.sign != this.sign ? -1 : 1;
        int cmp = this.compareToAbs(other);
        ArrayList<Integer> longer = cmp >= 0 ? this.digits : other.digits;
        ArrayList<Integer> shorter = cmp < 0 ? this.digits : other.digits;
        boolean longerSgn = cmp >= 0 ? this.sign : other.sign;

        int offset = longer.size() - shorter.size();

        for (int i = longer.size() - 1; i >= 0; i--) {
            if (i >= offset) {
                summ = addon + longer.get(i) + mult * shorter.get(i - offset);
            } else {
                summ = addon + longer.get(i);
            }

            if (summ > 0) {
                addon = summ > 9 ? 1 : 0;
                result.add(0, Math.abs(summ % 10));
            } else {
                addon = summ == 0 ? 0 : -1;
                result.add(0, (summ % 10 == 0) ? 0 : (10 + summ % 10));
            }
        }

        if (addon > 0) {
            result.add(0, addon);
        }

        while (result.size() > 1 && result.get(0) == 0) {
            result.remove(0);
        }

        boolean sign = addon >= 0;
        if (this.sign == false && other.sign == false)
            sign = false;
        else if (sign && mult == -1)
            sign = longerSgn;

        return new BigInt(result, sign);
    }

    public BigInt subtract(BigInt other) {
        BigInt neg = new BigInt(other.digits, !other.sign);
        return add(neg);
    }

    BigInt multiply(BigInt other) {
        if (isZero() || other.isZero()) {
            return BigInt.valueOf(0);
        }

        int addon = 0;
        BigInt a = new BigInt();
        BigInt b = new BigInt();
        int counter = 0;
        for (int i = other.digits.size() - 1; i >= 0; i--) {
            int current = other.digits.get(i);
            for (int j = this.digits.size() - 1; j >= 0; j--) {
                a.digits.add(0, (addon + current * this.digits.get(j)) % 10);
                addon = (addon + current * this.digits.get(j)) / 10;
            }
            ;
            if (addon != 0) {
                a.digits.add(0, addon);
                addon = 0;
            }
            int tmp = counter;
            while (tmp != 0) {
                a.digits.add(0);
                tmp = tmp - 1;
            }
            b = b.add(a);
            a.digits.clear();
            counter++;
        }

        if (!other.sign && this.sign || !this.sign && other.sign) {
            b.sign = false;
        }

        return b;
    }

    private boolean isZero() {
        return digits.size() == 1 && digits.get(0) == 0;
    }

    private boolean isNegative() {
        return !sign;
    }

    BigInt divide(BigInt other) {
        if (other.isZero())
            throw new ArithmeticException();
        if (this.isZero())
            return new BigInt("0");
        if (other.compareToAbs(BigInt.valueOf(1)) == 0)
            return this;
        int compareResult = this.compareToAbs(other);
        if (compareResult == 0)
            return new BigInt("1");
        else if (compareResult < 0)
            return new BigInt("0");

        BigInt result = new BigInt("0");
        BigInt delim = new BigInt(other.digits, true);

        BigInt temp = new BigInt("0");
        for (int i = 0; i < this.digits.size(); ++i) {
            temp.digits.add(this.digits.get(i));
            temp.clearZeros();

            if (temp.compareTo(delim) < 0) {
                result.digits.add(0);
                continue;
            }

            while (temp.compareTo(delim) >= 0) {
                temp = temp.subtract(delim);
                if (result.digits.size() <= i) {
                    result.digits.add(1);
                } else {
                    result.digits.set(i, result.digits.get(i) + 1);
                }
            }
        }

        result.clearZeros();

        if (this.isNegative() != other.isNegative())
            result.sign = false;

        return result;
    }

    protected void clearZeros() {
        while (digits.size() > 0 && digits.get(0) == 0) {
            digits.remove(0);
        }
    }

    public int compareToAbs(BigInt other) {
        return new BigInt(digits, true).compareTo(new BigInt(other.digits, true));
    }

    public int compareTo(BigInt other) {
        if (isNegative() && !other.isNegative())
            return -1;
        else if (!isNegative() && other.isNegative()) {
            return 1;
        } else if (isNegative()) {
            if (digits.size() > other.digits.size())
                return -1;
            else if (digits.size() < other.digits.size())
                return 1;
            else
                for (int index = 0; index < digits.size(); index++) {
                    if (digits.get(index) > other.digits.get(index))
                        return -1;
                    else if (digits.get(index) < other.digits.get(index))
                        return 1;
                }
            return 0;
        }
        if (digits.size() > other.digits.size()) {
            return 1;
        } else if (digits.size() < other.digits.size())
            return -1;
        else
            for (int index = 0; index < digits.size(); index++) {
                if (digits.get(index) > other.digits.get(index))
                    return 1;
                else if (digits.get(index) < other.digits.get(index))
                    return -1;
            }

        return 0;
    }

}
