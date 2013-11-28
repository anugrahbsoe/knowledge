import java.util.List;
import java.util.Map;

class WhenAllCondition implements Condition
{
	protected List<Condition> conditions;

	public WhenAllCondition(List<Condition> conditions)
	{
		this.conditions = conditions;
	} 

	public boolean isTrue(Map<String, String> facts)
	{
		for (Condition condition : conditions)
			if (!condition.isTrue(facts))
				return false;

		return true;
	}
}