package contract.matcher;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.experimental.theories.PotentialAssignment.forValue;

public class InvalidEmailAddressSupplier extends ParameterSupplier {
    @Override
    public List<PotentialAssignment> getValueSources(final ParameterSignature signature) {
        return asList(
                forValue("Abc.example.com", "Abc.example.com"),
                forValue("Abc.@example.com", "Abc.@example.com"),
                forValue("A@b@c@example.com", "A@b@c@example.com"),
                forValue("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com", "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"),
                forValue("empty string", ""));
    }
}
