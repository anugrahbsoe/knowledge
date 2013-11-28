import java.util.Scanner;

class CliSolver extends Solver
{
	public String ask(Question question)
	{
		System.out.println(question.question);

		int i;

		for (i = 0; i < question.options.size(); ++i)
			System.out.println(i + ") " + question.options.get(i).name);

		int answer = getInput(i);

		return question.options.get(answer).value;
	}

	private int getInput(int max)
	{
		int answer = -1;

		while (answer < 0 || answer > max)
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter an integer:");
			answer = keyboard.nextInt();
		}

		return answer;
	}	
}
