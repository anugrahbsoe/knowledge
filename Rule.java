class Rule
{
	protected Condition condition;

	protected Map<String,String> facts;

	public Rule(Condition condition, Map<String, String> facts)
	{
		this.condition = condition;

		this.facts = facts;
	}

	public boolean canInfer(String factName)
	{
		return facts.containsKey(factName);
	} 

	public boolean isTrue(Map<String,String> facts)
	{
		return condition.isTrue(facts);
	}

	public void apply(Map<String,String> facts)
	{
		facts.putAll(facts);
	}
}