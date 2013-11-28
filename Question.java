import java.util.Map;

class Question
{
	public String factName;
	
	public Question question;

	public List<Option> answers;

	public Question(String factName, String question, List<Option> answers)
	{
		this.factName = factName;

		this.question = question;

		this.answers = answers;
	}

	public boolean canInfer(String fact)
	{
		return factName.equals(fact);
	}
}