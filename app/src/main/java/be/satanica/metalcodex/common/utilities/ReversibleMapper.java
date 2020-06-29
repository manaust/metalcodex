package be.satanica.metalcodex.common.utilities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ReversibleMapper<T1, T2> extends Mapper<T1, T2> {

    public abstract T1 reverseMap(T2 input);

    public final List<T1> reverseMap(@NonNull List<T2> inputs) {
        List<T1> output = new ArrayList<>();
        for (T2 input : inputs) {
            output.add(reverseMap(input));
        }
        return output;
    }
}
