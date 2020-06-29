package be.satanica.metalcodex.common.utilities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<T1, T2> {

    public abstract T2 map(T1 input);

    public final List<T2> map(@NonNull List<T1> inputs) {
        List<T2> output = new ArrayList<>();
        for (T1 input : inputs) {
            output.add(map(input));
        }
        return output;
    }
}
