.class public Lcom/zhc/u/s;
.super Ljava/lang/Object;
.source "s.java"


# instance fields
.field private b:Z


# direct methods
.method public constructor <init>()V
    .registers 2

    .prologue
    .line 97
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 98
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/zhc/u/s;->b:Z

    return-void
.end method

.method private f1()V
    .registers 3

    .prologue
    .line 101
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(I)V

    .line 102
    return-void
.end method

.method public static main([Ljava/lang/String;)V
    .registers 6
    .param p0, "args"    # [Ljava/lang/String;

    .prologue
    const/4 v4, 0x1

    .line 109
    new-instance v0, Lcom/zhc/u/s;

    invoke-direct {v0}, Lcom/zhc/u/s;-><init>()V

    .line 110
    .local v0, "s":Lcom/zhc/u/s;
    invoke-direct {v0}, Lcom/zhc/u/s;->f1()V

    .line 111
    sget-object v1, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "s.f2(\"a.\") = "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "a."

    invoke-virtual {v0, v3}, Lcom/zhc/u/s;->f2(Ljava/lang/String;)I

    move-result v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 112
    new-instance v1, Lcom/zhc/u/s;

    invoke-direct {v1}, Lcom/zhc/u/s;-><init>()V

    iput-boolean v4, v1, Lcom/zhc/u/s;->b:Z

    .line 113
    iput-boolean v4, v0, Lcom/zhc/u/s;->b:Z

    .line 114
    return-void
.end method


# virtual methods
.method public f2(Ljava/lang/String;)I
    .registers 3
    .param p1, "s"    # Ljava/lang/String;

    .prologue
    .line 105
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    return v0
.end method
