package cyclops.monads.transformers;


import com.oath.cyclops.types.foldable.ConvertableSequence;
import cyclops.companion.rx.Observables;
import cyclops.monads.AnyMs;
import cyclops.monads.Witness;


public class StreamTSeqConvertableSequenceTest extends AbstractConvertableSequenceTest {

    @Override
    public <T> ConvertableSequence<T> of(T... elements) {

        return AnyMs.liftM(Observables.of(elements),Witness.list.INSTANCE).to();
    }

    @Override
    public <T> ConvertableSequence<T> empty() {

        return AnyMs.liftM(Observables.<T>empty(),Witness.list.INSTANCE).to();
    }

}
