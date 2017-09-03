package xyz.gnarbot.gnar.utils;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import xyz.gnarbot.gnar.Bot;
import xyz.gnarbot.gnar.Shard;
import xyz.gnarbot.gnar.guilds.GuildData;
import xyz.gnarbot.gnar.utils.response.GuildResponseBuilder;
import xyz.gnarbot.gnar.utils.response.ResponseBuilder;

public final class Context {
    private final Message message;
    private final TextChannel channel;
    private final Guild guild;
    private final GuildData guildOptions;
    private final JDA jda;
    private final Shard shard;
    private final Member member;
    private final User user;

    public Context(GuildMessageReceivedEvent event) {
        this(event, Bot.getOptions().ofGuild(event.getGuild()));
    }

    public Context(GuildMessageReceivedEvent event, GuildData guildOptions) {
        this.message = event.getMessage();
        this.channel = event.getChannel();
        this.guild = event.getGuild();
        this.guildOptions = guildOptions == null ? Bot.getOptions().ofGuild(guild) : guildOptions;
        this.jda = event.getJDA();
        this.shard = Bot.getShard(this.jda);
        this.member = event.getMember();
        this.user = event.getAuthor();
    }

    public final Message getMessage() {
        return this.message;
    }

    public final TextChannel getChannel() {
        return this.channel;
    }

    public final Guild getGuild() {
        return this.guild;
    }

    public final GuildData getData() {
        return this.guildOptions;
    }

    public final JDA getJDA() {
        return this.jda;
    }

    public final Shard getShard() {
        return this.shard;
    }

    public final Member getMember() {
        return this.member;
    }

    public final Member getSelfMember() {
        return this.guild.getSelfMember();
    }

    public final VoiceChannel getVoiceChannel() {
        return getMember().getVoiceState().getChannel();
    }

    public final User getUser() {
        return this.user;
    }

    public final ResponseBuilder send() {
        return new GuildResponseBuilder(channel);
    }
}