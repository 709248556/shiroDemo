import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class test {
	 //����һ��ȫ�ֵļ�¼����ͨ��LoggerFactory��ȡ
    private static final Logger logger = LoggerFactory.getLogger(test.class); 
    /**test
    * @param args
    */
   public static void main(String[] args) {
       logger.info("logback �ɹ���");
       logger.error("logback �ɹ���");
   }
}
