class IsEqualCondition implements Condition
{
	public IsEqualCondition(String factName, String value)
	{
		this.factName = factName;

		this.value = value;
	}

	public boolean isTrue(Map<String,String> facts)
	{
		return facts.containsKey(factName)
			&& facts.get(factName).equals(value);
	}
}