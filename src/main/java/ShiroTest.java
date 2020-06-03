import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test() {
//1. Load the INI configuration
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_ini.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        subject.login(token);

        for (Object principal : subject.getPrincipals().asList()) {
            System.out.println(principal);
        }
        subject.checkPermission("hp:download:xjq");
        subject.checkPermission("hp:watch:xjq");
    }
}
