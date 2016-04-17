package name.mlnkrishnan.shouldJ.primitive;

public class BoxPrimitive {

    public static Integer[] from(int[] array) {
        Integer[] newArray = new Integer[array.length];
        int i = 0;
        for (int value : array) {
            newArray[i++] = Integer.valueOf(value);
        }
        return newArray;
    }
}
