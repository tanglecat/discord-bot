package li.tanglecat.bot;

import javax.security.auth.login.LoginException;
import li.tanglecat.bot.listener.SpecListener;
import li.tanglecat.bot.listener.ToggleListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Launcher {

    public static void main(final String[] args) throws LoginException, InterruptedException {
        Settings.SPEC_CHANNEL_ID = Long.parseLong(System.getenv("TCB_SPEC_ID"));
        Settings.ENABLE_SPEC_P = true;

        final JDA jda = JDABuilder.createDefault(
                System.getenv("TCB_TOKEN"),
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGES
        ).addEventListeners(
                new SpecListener(),
                new ToggleListener()
        ).build().awaitReady();

        jda.getPresence().setPresence(OnlineStatus.DO_NOT_DISTURB, Activity.watching("#spec"));
    }

}
