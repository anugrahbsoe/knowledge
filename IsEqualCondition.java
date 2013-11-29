import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class IsEqualCondition implements Condition
{
	private String name;

	private String value;

	public IsEqualCondition(String name, String value)
	{
		this.name = name;

		this.value = value;
	}

	public boolean isTrue(Map<String,String> facts)
	{
		return facts.containsKey(name)
			&& facts.get(name).equals(value);
	}

	public List<String> getRequiredFacts()
	{
		List<String> facts = new ArrayList<String>(1);
		facts.add(name);
		return facts;
	}
}