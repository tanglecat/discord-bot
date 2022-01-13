package li.tanglecat.bot.listener;

import java.util.concurrent.TimeUnit;
import li.tanglecat.bot.Settings;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ToggleListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull final MessageReceivedEvent event) {
        if (event.getChannel() instanceof TextChannel
                && event.getMember() != null
                && event.getMember().hasPermission(Permission.MESSAGE_MANAGE)
                && !event.getAuthor().isBot()
                && event.getMessage().getContentRaw().toLowerCase().matches("\\$toggle [a-zA-Z0-9\\-_]+")) {
            final String toToggle = event.getMessage().getContentRaw().substring(8);
            switch (toToggle.toLowerCase()) {
                case "spec_p":
                    Settings.ENABLE_SPEC_P = !Settings.ENABLE_SPEC_P;
                    event.getMessage().reply("Spec P: " + (Settings.ENABLE_SPEC_P ? "On" : "Off"))
                            .queue(r -> r.delete().queueAfter(5, TimeUnit.SECONDS));
                    break;
                default:
                    event.getMessage().reply("Unknown toggle value")
                            .queue(r -> r.delete().queueAfter(5, TimeUnit.SECONDS));
                    break;
            }
        }
    }
}
