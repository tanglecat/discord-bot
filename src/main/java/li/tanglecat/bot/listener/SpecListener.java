package li.tanglecat.bot.listener;

import java.util.concurrent.TimeUnit;
import li.tanglecat.bot.Settings;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SpecListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull final MessageReceivedEvent event) {
        final MessageChannel channel = event.getChannel();
        if (channel instanceof TextChannel textChannel
                && textChannel.getIdLong() == Settings.SPEC_CHANNEL_ID
                && event.getMessage().getContentRaw().equalsIgnoreCase("p")
                && !event.getAuthor().isBot()
                && Settings.ENABLE_SPEC_P) {
            event.getMessage().reply("p").queue(msg -> {
                event.getMessage().addReaction("\uD83C\uDDF5").queue();
                msg.delete().queueAfter(3, TimeUnit.SECONDS);
            });
        }
    }

}
