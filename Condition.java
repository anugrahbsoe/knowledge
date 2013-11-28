import java.util.Map;

interface Condition 
{
	public boolean isTrue(Map<String, String> facts);
}