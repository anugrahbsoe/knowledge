import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

abstract class Solver
{
	public void solve(List<Rule> rules, List<Question> questions, String initialGoal)
	{
		Map<String, String> facts = new HashMap<String, String>();

		Stack<String> goalStack = new Stack<String>();

		goalStack.push(initialGoal);

		// While we still have things to prove…
		while (!goalStack.empty())
		{
			String goal = goalStack.pop();

			// Have we already inferred this goal? Skip it!
			if (facts.containsKey(goal))
				continue;

			// Do we have a question to infer this goal? Ask it and go to the
			// next goal.
			for (Question question : questions)
			{
				if (question.canInfer(goal))
				{
					facts.put(goal, ask(question));

					forwardChain(rules, facts);

					continue;
				}
			}

			// Calculate which fact we have to find next to move on
			backwardChain(rules, facts, goal, goalStack);
		}
	}

	private void forwardChain(List<Rule> rules, Map<String, String> facts)
	{
		for (boolean knowledgeChanged = true; knowledgeChanged; )
		{
			knowledgeChanged = false;

			// Try all rules
			for (Rule rule : rules)
			{
				// If the rule infers facts that are already known, skip it
				if (skipRule(rule, facts))
					continue;

				// See whether the rule is true
				if (rule.isTrue(facts))
				{
					// True! apply the consequences
					rule.apply(facts);

					// The knowledge has changed, repeat forward chaining
					knowledgeChanged = true;
					break;
				}
			}
		}
	}

	private void backwardChain(List<Rule> rules, Map<String,String> facts, String goal, Stack<String> goalStack)
	{
		Set<String> relevantConditionFacts = new HashSet<String>();

		for (Rule rule : rules)
		{
			if (skipRule(rule, facts))
				continue;

			if (!rule.canInfer(goal))
				continue;

			List<String> conditionFacts = rule.getRequiredFacts();

			for (String fact : conditionFacts)
				if (!facts.containsKey(fact))
					relevantConditionFacts.add(fact);
		}

		for (String fact : relevantConditionFacts)
			if (!goalStack.contains(fact))
				goalStack.push(fact);
	}

	private boolean skipRule(Rule rule, Map<String, String> facts)
	{
		for (String fact : facts.keySet())
			if (rule.canInfer(fact))
				return true;
		
		return false;
	}

	protected abstract String ask(Question question);
}