import java.util.List;

class Question
{
	public String factName;

	public String question;

	public List<Option> options;

	public Question(String factName, String question, List<Option> options)
	{
		this.factName = factName;

		this.question = question;

		this.options = options;
	}

	public boolean canInfer(String fact)
	{
		return factName.equals(fact);
	}
}