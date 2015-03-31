package Arrays;

public class Test 
{   public void add(int a)
	{
		loop: for (int i=2;i<3;i++)
		{ for(int j=1;j<3;j++)
		{  if(a==5)
			{
				break loop;
			}	
		
		System.out.println(i*j);
		}
		}
	}
	public static void main(String[] args) {
		Test x = new Test();
		x.add(5);

	}

}
