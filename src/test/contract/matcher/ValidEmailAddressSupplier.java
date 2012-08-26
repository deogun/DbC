package contract.matcher;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.experimental.theories.PotentialAssignment.forValue;

public class ValidEmailAddressSupplier extends ParameterSupplier {
    @Override
    public List<PotentialAssignment> getValueSources(final ParameterSignature signature) {
        return asList(
                forValue("someaddress@example.com", "someaddress@example.com"),
                forValue("simplewith+symbol@example.com", "simplewith+symbol@example.com"),
                forValue("quite.common@example.com", "quite.common@example.com"),
                forValue("a.little.more.unusual@dept.example.com", "a.little.more.unusual@dept.example.com"),
                forValue("'@[10.10.10.10]", "'@[10.10.10.10]"),
                forValue("!#$%&'*+-/=?^_`{}|~@example.org", "!#$%&'*+-/=?^_`{}|~@example.org"));
    }
}
