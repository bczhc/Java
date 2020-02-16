.class public Lcom/zhc/u/Base128;
.super Ljava/lang/Object;
.source "Base128.java"


# static fields
.field static final synthetic $assertionsDisabled:Z

.field private static FileSize:J

.field public static o:Lcom/zhc/u/Base128;


# instance fields
.field private string:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .registers 2

    .prologue
    .line 31
    const-class v0, Lcom/zhc/u/Base128;

    invoke-virtual {v0}, Ljava/lang/Class;->desiredAssertionStatus()Z

    move-result v0

    if-nez v0, :cond_17

    const/4 v0, 0x1

    :goto_9
    sput-boolean v0, Lcom/zhc/u/Base128;->$assertionsDisabled:Z

    .line 33
    new-instance v0, Lcom/zhc/u/Base128;

    invoke-direct {v0}, Lcom/zhc/u/Base128;-><init>()V

    sput-object v0, Lcom/zhc/u/Base128;->o:Lcom/zhc/u/Base128;

    .line 36
    const-wide/16 v0, 0x0

    sput-wide v0, Lcom/zhc/u/Base128;->FileSize:J

    return-void

    .line 31
    :cond_17
    const/4 v0, 0x0

    goto :goto_9
.end method

.method private constructor <init>()V
    .registers 1

    .prologue
    .line 42
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 43
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .registers 2
    .param p1, "s"    # Ljava/lang/String;

    .prologue
    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 39
    iput-object p1, p0, Lcom/zhc/u/Base128;->string:Ljava/lang/String;

    .line 40
    return-void
.end method

.method private NumStr_lenTo(Ljava/lang/String;I)Ljava/lang/String;
    .registers 7
    .param p1, "string"    # Ljava/lang/String;
    .param p2, "to"    # I

    .prologue
    .line 186
    const/4 v1, 0x0

    .line 187
    .local v1, "j":Z
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    .line 188
    .local v2, "sb":Ljava/lang/StringBuilder;
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v3

    if-ge v3, p2, :cond_20

    .line 189
    const/4 v1, 0x1

    .line 190
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_e
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v3

    sub-int v3, p2, v3

    if-ge v0, v3, :cond_1d

    .line 191
    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 190
    add-int/lit8 v0, v0, 0x1

    goto :goto_e

    .line 193
    :cond_1d
    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 195
    .end local v0    # "i":I
    :cond_20
    if-eqz v1, :cond_26

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .end local p1    # "string":Ljava/lang/String;
    :cond_26
    return-object p1
.end method

.method private String_56_DivideInto(Ljava/lang/String;I)[Ljava/lang/String;
    .registers 8
    .param p1, "s"    # Ljava/lang/String;
    .param p2, "per_char_split"    # I

    .prologue
    const/16 v4, 0x38

    .line 226
    sget-boolean v2, Lcom/zhc/u/Base128;->$assertionsDisabled:Z

    if-nez v2, :cond_12

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    if-ne v2, v4, :cond_12

    new-instance v2, Ljava/lang/AssertionError;

    invoke-direct {v2}, Ljava/lang/AssertionError;-><init>()V

    throw v2

    .line 227
    :cond_12
    div-int v2, v4, p2

    new-array v1, v2, [Ljava/lang/String;

    .line 228
    .local v1, "r":[Ljava/lang/String;
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_17
    div-int v2, v4, p2

    if-ge v0, v2, :cond_29

    .line 229
    mul-int v2, p2, v0

    add-int/lit8 v3, v0, 0x1

    mul-int/2addr v3, p2

    invoke-virtual {p1, v2, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v1, v0

    .line 228
    add-int/lit8 v0, v0, 0x1

    goto :goto_17

    .line 231
    :cond_29
    return-object v1
.end method

.method private _7Return_8([I)[B
    .registers 11
    .param p1, "ints"    # [I

    .prologue
    const/16 v8, 0x8

    const/4 v7, 0x7

    .line 212
    new-array v1, v8, [B

    .line 213
    .local v1, "r":[B
    sget-boolean v4, Lcom/zhc/u/Base128;->$assertionsDisabled:Z

    if-nez v4, :cond_12

    array-length v4, p1

    if-ne v4, v7, :cond_12

    new-instance v4, Ljava/lang/AssertionError;

    invoke-direct {v4}, Ljava/lang/AssertionError;-><init>()V

    throw v4

    .line 214
    :cond_12
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    .line 215
    .local v2, "sb":Ljava/lang/StringBuilder;
    array-length v5, p1

    const/4 v4, 0x0

    :goto_19
    if-ge v4, v5, :cond_2b

    aget v0, p1, v4

    .line 216
    .local v0, "i":I
    invoke-static {v0}, Ljava/lang/Integer;->toBinaryString(I)Ljava/lang/String;

    move-result-object v6

    invoke-direct {p0, v6, v8}, Lcom/zhc/u/Base128;->NumStr_lenTo(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 215
    add-int/lit8 v4, v4, 0x1

    goto :goto_19

    .line 218
    .end local v0    # "i":I
    :cond_2b
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v4, v7}, Lcom/zhc/u/Base128;->String_56_DivideInto(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v3

    .line 219
    .local v3, "strings":[Ljava/lang/String;
    const/4 v0, 0x0

    .restart local v0    # "i":I
    :goto_34
    array-length v4, v3

    if-ge v0, v4, :cond_44

    .line 220
    aget-object v4, v3, v0

    const/4 v5, 0x2

    invoke-static {v4, v5}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v4

    int-to-byte v4, v4

    aput-byte v4, v1, v0

    .line 219
    add-int/lit8 v0, v0, 0x1

    goto :goto_34

    .line 222
    :cond_44
    return-object v1
.end method

.method private check_extracted(Ljava/lang/String;Ljava/io/File;Ljava/io/File;)[Ljava/lang/String;
    .registers 9
    .param p1, "aArg_EnOrDe"    # Ljava/lang/String;
    .param p2, "file"    # Ljava/io/File;
    .param p3, "dest"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 249
    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v4, "\u6b63\u5728\u6821\u9a8c..."

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->print(Ljava/lang/String;)V

    .line 250
    new-instance v0, Ljava/io/File;

    invoke-virtual {p3}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v0, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 251
    .local v0, "ckF":Ljava/io/File;
    new-instance v3, Lcom/zhc/u/FileU;

    invoke-direct {v3}, Lcom/zhc/u/FileU;-><init>()V

    invoke-virtual {v3, v0}, Lcom/zhc/u/FileU;->creatFile_SameFileName(Ljava/io/File;)Ljava/io/File;

    move-result-object v1

    .line 252
    .local v1, "new_ckF":Ljava/io/File;
    const-string v3, "En"

    invoke-virtual {p1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_41

    .line 253
    new-instance v3, Ljava/io/FileInputStream;

    invoke-direct {v3, p3}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    new-instance v4, Ljava/io/FileOutputStream;

    invoke-direct {v4, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    invoke-static {v3, v4}, Lcom/zhc/u/Base128;->encode(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 257
    :cond_2e
    :goto_2e
    new-instance v3, Lcom/zhc/u/Base128;

    invoke-direct {v3}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v3, p2, v1}, Lcom/zhc/u/Base128;->ck_extracted(Ljava/io/File;Ljava/io/File;)Ljava/util/List;

    move-result-object v3

    const/4 v4, 0x0

    new-array v4, v4, [Ljava/lang/String;

    invoke-interface {v3, v4}, Ljava/util/List;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [Ljava/lang/String;

    .line 258
    .local v2, "r":[Ljava/lang/String;
    return-object v2

    .line 254
    .end local v2    # "r":[Ljava/lang/String;
    :cond_41
    const-string v3, "De"

    invoke-virtual {p1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2e

    .line 255
    new-instance v3, Ljava/io/FileInputStream;

    invoke-direct {v3, p3}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    new-instance v4, Ljava/io/FileOutputStream;

    invoke-direct {v4, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    invoke-static {v3, v4}, Lcom/zhc/u/Base128;->decode(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    goto :goto_2e
.end method

.method private ck_extracted(Ljava/io/File;Ljava/io/File;)Ljava/util/List;
    .registers 9
    .param p1, "f1"    # Ljava/io/File;
    .param p2, "new_ckF"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/File;",
            "Ljava/io/File;",
            ")",
            "Ljava/util/List",
            "<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 80
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 81
    .local v2, "r":Ljava/util/List;, "Ljava/util/List<Ljava/lang/String;>;"
    invoke-static {p2}, Lcom/zhc/u/FileU;->getMD5String(Ljava/io/File;)Ljava/lang/String;

    move-result-object v0

    .line 82
    .local v0, "md5_1":Ljava/lang/String;
    invoke-static {p1}, Lcom/zhc/u/FileU;->getMD5String(Ljava/io/File;)Ljava/lang/String;

    move-result-object v1

    .line 83
    .local v1, "md5_2":Ljava/lang/String;
    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_50

    .line 84
    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "\u6821\u9a8c\u901a\u8fc7\uff01\nMD5: "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->print(Ljava/lang/String;)V

    .line 85
    const-string v3, "true"

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 86
    invoke-interface {v2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 94
    :goto_33
    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "\ndelete: "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {p2}, Ljava/io/File;->delete()Z

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 95
    return-object v2

    .line 88
    :cond_50
    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "\u6821\u9a8c\u672a\u901a\u8fc7\u3002\u3002\u3002\u3002\n"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\n"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 90
    const-string v3, "false"

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 91
    invoke-interface {v2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 92
    invoke-interface {v2, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_33
.end method

.method public static decode(Ljava/io/InputStream;)Ljava/io/OutputStream;
    .registers 3
    .param p0, "in"    # Ljava/io/InputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 109
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 110
    .local v0, "r":Ljava/io/ByteArrayOutputStream;
    new-instance v1, Lcom/zhc/u/Base128;

    invoke-direct {v1}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v1, p0, v0}, Lcom/zhc/u/Base128;->decode_extracted(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 111
    return-object v0
.end method

.method public static decode(Ljava/io/File;Ljava/io/File;)V
    .registers 4
    .param p0, "file"    # Ljava/io/File;
    .param p1, "dest"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 279
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const/4 v1, 0x0

    invoke-static {p0, p1, v1}, Lcom/zhc/u/Base128;->decode(Ljava/io/File;Ljava/io/File;Z)[Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 280
    return-void
.end method

.method public static decode(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    .registers 3
    .param p0, "in"    # Ljava/io/InputStream;
    .param p1, "to_out"    # Ljava/io/OutputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 115
    new-instance v0, Lcom/zhc/u/Base128;

    invoke-direct {v0}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v0, p0, p1}, Lcom/zhc/u/Base128;->decode_extracted(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 116
    return-void
.end method

.method public static decode(Ljava/io/File;Ljava/io/File;Z)[Ljava/lang/String;
    .registers 9
    .param p0, "file"    # Ljava/io/File;
    .param p1, "dest"    # Ljava/io/File;
    .param p2, "check"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v5, 0x0

    .line 262
    new-array v2, v5, [Ljava/lang/String;

    .line 263
    .local v2, "r":[Ljava/lang/String;
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v3

    if-nez v3, :cond_12

    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {p0}, Ljava/io/File;->createNewFile()Z

    move-result v4

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->println(Z)V

    .line 264
    :cond_12
    new-instance v0, Ljava/io/FileInputStream;

    invoke-direct {v0, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    .line 265
    .local v0, "is2":Ljava/io/InputStream;
    new-instance v1, Ljava/io/FileOutputStream;

    invoke-direct {v1, p1, v5}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;Z)V

    .line 266
    .local v1, "os2":Ljava/io/OutputStream;
    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v4, "\u6b63\u5728\u89e3\u7801..."

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 267
    invoke-static {v0, v1}, Lcom/zhc/u/Base128;->decode(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 268
    if-eqz p2, :cond_33

    .line 269
    new-instance v3, Lcom/zhc/u/Base128;

    invoke-direct {v3}, Lcom/zhc/u/Base128;-><init>()V

    const-string v4, "En"

    invoke-direct {v3, v4, p0, p1}, Lcom/zhc/u/Base128;->check_extracted(Ljava/lang/String;Ljava/io/File;Ljava/io/File;)[Ljava/lang/String;

    move-result-object v2

    .line 271
    :cond_33
    return-object v2
.end method

.method private decode_extracted(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    .registers 25
    .param p1, "in"    # Ljava/io/InputStream;
    .param p2, "to_out"    # Ljava/io/OutputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 119
    invoke-virtual/range {p1 .. p1}, Ljava/io/InputStream;->available()I

    move-result v16

    move/from16 v0, v16

    int-to-long v0, v0

    move-wide/from16 v16, v0

    sput-wide v16, Lcom/zhc/u/Base128;->FileSize:J

    .line 120
    const/16 v16, 0x8

    move/from16 v0, v16

    new-array v5, v0, [B

    .line 121
    .local v5, "b":[B
    const-wide/16 v12, 0x0

    .line 122
    .local v12, "j":J
    const/16 v16, 0x1

    move/from16 v0, v16

    new-array v4, v0, [B

    .line 123
    .local v4, "F_f":[B
    sget-object v16, Ljava/lang/System;->out:Ljava/io/PrintStream;

    move-object/from16 v0, p1

    invoke-virtual {v0, v4}, Ljava/io/InputStream;->read([B)I

    move-result v17

    invoke-virtual/range {v16 .. v17}, Ljava/io/PrintStream;->println(I)V

    .line 124
    const/16 v16, 0x0

    aget-byte v10, v4, v16

    .line 125
    .local v10, "f":I
    sget-object v16, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v17, Ljava/lang/StringBuilder;

    invoke-direct/range {v17 .. v17}, Ljava/lang/StringBuilder;-><init>()V

    const-string v18, "skip: "

    invoke-virtual/range {v17 .. v18}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v17

    const-wide/16 v18, 0x7

    move-object/from16 v0, p1

    move-wide/from16 v1, v18

    invoke-virtual {v0, v1, v2}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v18

    invoke-virtual/range {v17 .. v19}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v17

    invoke-virtual/range {v17 .. v17}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v17

    invoke-virtual/range {v16 .. v17}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 127
    :cond_4a
    :goto_4a
    const/16 v16, 0x7

    move/from16 v0, v16

    new-array v8, v0, [B

    .line 128
    .local v8, "b3":[B
    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    .line 129
    .local v15, "sb":Ljava/lang/StringBuilder;
    move-object/from16 v0, p1

    invoke-virtual {v0, v5}, Ljava/io/InputStream;->read([B)I

    move-result v16

    const/16 v17, -0x1

    move/from16 v0, v16

    move/from16 v1, v17

    if-eq v0, v1, :cond_12d

    .line 130
    const-wide/16 v16, 0x8

    add-long v12, v12, v16

    .line 131
    array-length v0, v5

    move/from16 v17, v0

    const/16 v16, 0x0

    :goto_6c
    move/from16 v0, v16

    move/from16 v1, v17

    if-ge v0, v1, :cond_8b

    aget-byte v6, v5, v16

    .line 132
    .local v6, "b1":B
    new-instance v18, Lcom/zhc/u/Base128;

    invoke-direct/range {v18 .. v18}, Lcom/zhc/u/Base128;-><init>()V

    invoke-static {v6}, Ljava/lang/Integer;->toBinaryString(I)Ljava/lang/String;

    move-result-object v19

    const/16 v20, 0x7

    invoke-direct/range {v18 .. v20}, Lcom/zhc/u/Base128;->NumStr_lenTo(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v18

    move-object/from16 v0, v18

    invoke-virtual {v15, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 131
    add-int/lit8 v16, v16, 0x1

    goto :goto_6c

    .line 134
    .end local v6    # "b1":B
    :cond_8b
    new-instance v16, Lcom/zhc/u/Base128;

    invoke-direct/range {v16 .. v16}, Lcom/zhc/u/Base128;-><init>()V

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v17

    const/16 v18, 0x8

    invoke-direct/range {v16 .. v18}, Lcom/zhc/u/Base128;->String_56_DivideInto(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v14

    .line 135
    .local v14, "s":[Ljava/lang/String;
    const/4 v11, 0x0

    .local v11, "i":I
    :goto_9b
    array-length v0, v14

    move/from16 v16, v0

    move/from16 v0, v16

    if-ge v11, v0, :cond_c2

    .line 136
    aget-object v16, v14, v11

    const/16 v17, 0x2

    invoke-static/range {v16 .. v17}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v7

    .line 137
    .local v7, "b2":I
    neg-int v0, v7

    move/from16 v16, v0

    move/from16 v0, v16

    and-int/lit16 v0, v0, 0xff

    move/from16 v16, v0

    move/from16 v0, v16

    neg-int v0, v0

    move/from16 v16, v0

    move/from16 v0, v16

    int-to-byte v0, v0

    move/from16 v16, v0

    aput-byte v16, v8, v11

    .line 135
    add-int/lit8 v11, v11, 0x1

    goto :goto_9b

    .line 139
    .end local v7    # "b2":I
    :cond_c2
    invoke-virtual/range {p1 .. p1}, Ljava/io/InputStream;->available()I

    move-result v16

    const/16 v17, 0x8

    move/from16 v0, v16

    move/from16 v1, v17

    if-lt v0, v1, :cond_10e

    .line 140
    move-object/from16 v0, p2

    invoke-virtual {v0, v8}, Ljava/io/OutputStream;->write([B)V

    .line 147
    .end local v8    # "b3":[B
    :goto_d3
    const-wide/32 v16, 0x100000

    rem-long v16, v12, v16

    const-wide/16 v18, 0x0

    cmp-long v16, v16, v18

    if-nez v16, :cond_4a

    .line 148
    sget-object v16, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v17, Ljava/lang/StringBuilder;

    invoke-direct/range {v17 .. v17}, Ljava/lang/StringBuilder;-><init>()V

    const-string v18, "progress: "

    invoke-virtual/range {v17 .. v18}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v17

    long-to-double v0, v12

    move-wide/from16 v18, v0

    sget-wide v20, Lcom/zhc/u/Base128;->FileSize:J

    move-wide/from16 v0, v20

    long-to-double v0, v0

    move-wide/from16 v20, v0

    div-double v18, v18, v20

    const-wide/high16 v20, 0x4059000000000000L    # 100.0

    mul-double v18, v18, v20

    invoke-virtual/range {v17 .. v19}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    move-result-object v17

    const-string v18, "%"

    invoke-virtual/range {v17 .. v18}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v17

    invoke-virtual/range {v17 .. v17}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v17

    invoke-virtual/range {v16 .. v17}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto/16 :goto_4a

    .line 142
    .restart local v8    # "b3":[B
    :cond_10e
    new-array v9, v10, [B

    .line 143
    .local v9, "b4":[B
    const/16 v16, 0x0

    const/16 v17, 0x0

    array-length v0, v9

    move/from16 v18, v0

    move/from16 v0, v16

    move/from16 v1, v17

    move/from16 v2, v18

    invoke-static {v8, v0, v9, v1, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 144
    array-length v0, v9

    move/from16 v16, v0

    if-nez v16, :cond_12b

    .end local v8    # "b3":[B
    :goto_125
    move-object/from16 v0, p2

    invoke-virtual {v0, v8}, Ljava/io/OutputStream;->write([B)V

    goto :goto_d3

    .restart local v8    # "b3":[B
    :cond_12b
    move-object v8, v9

    goto :goto_125

    .line 152
    .end local v9    # "b4":[B
    .end local v11    # "i":I
    .end local v14    # "s":[Ljava/lang/String;
    :cond_12d
    invoke-virtual/range {p2 .. p2}, Ljava/io/OutputStream;->flush()V

    .line 153
    invoke-virtual/range {p2 .. p2}, Ljava/io/OutputStream;->close()V

    .line 154
    sget-object v16, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v17, "progress: 100%\n\u89e3\u7801\u5b8c\u6210\u3002"

    invoke-virtual/range {v16 .. v17}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 156
    return-void
.end method

.method public static encode(Ljava/io/InputStream;)Ljava/io/OutputStream;
    .registers 3
    .param p0, "in"    # Ljava/io/InputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 99
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 100
    .local v0, "baos":Ljava/io/ByteArrayOutputStream;
    new-instance v1, Lcom/zhc/u/Base128;

    invoke-direct {v1}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v1, p0, v0}, Lcom/zhc/u/Base128;->encode_extracted(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 101
    return-object v0
.end method

.method public static encode(Ljava/io/File;Ljava/io/File;)V
    .registers 4
    .param p0, "file"    # Ljava/io/File;
    .param p1, "dest"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 275
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const/4 v1, 0x0

    invoke-static {p0, p1, v1}, Lcom/zhc/u/Base128;->encode(Ljava/io/File;Ljava/io/File;Z)[Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 276
    return-void
.end method

.method public static encode(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    .registers 3
    .param p0, "in"    # Ljava/io/InputStream;
    .param p1, "to_out"    # Ljava/io/OutputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 105
    new-instance v0, Lcom/zhc/u/Base128;

    invoke-direct {v0}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v0, p0, p1}, Lcom/zhc/u/Base128;->encode_extracted(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 106
    return-void
.end method

.method public static encode(Ljava/io/File;Ljava/io/File;Z)[Ljava/lang/String;
    .registers 9
    .param p0, "file"    # Ljava/io/File;
    .param p1, "dest"    # Ljava/io/File;
    .param p2, "check"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v5, 0x0

    .line 235
    new-array v2, v5, [Ljava/lang/String;

    .line 236
    .local v2, "r":[Ljava/lang/String;
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v3

    if-nez v3, :cond_12

    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {p1}, Ljava/io/File;->createNewFile()Z

    move-result v4

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->println(Z)V

    .line 237
    :cond_12
    new-instance v0, Ljava/io/FileOutputStream;

    invoke-direct {v0, p1, v5}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;Z)V

    .line 238
    .local v0, "fos":Ljava/io/FileOutputStream;
    new-instance v1, Ljava/io/FileInputStream;

    invoke-direct {v1, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    .line 239
    .local v1, "is":Ljava/io/InputStream;
    sget-object v3, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v4, "\u6b63\u5728\u7f16\u7801..."

    invoke-virtual {v3, v4}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 240
    invoke-static {v1, v0}, Lcom/zhc/u/Base128;->encode(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 241
    if-eqz p2, :cond_33

    .line 242
    new-instance v3, Lcom/zhc/u/Base128;

    invoke-direct {v3}, Lcom/zhc/u/Base128;-><init>()V

    const-string v4, "De"

    invoke-direct {v3, v4, p0, p1}, Lcom/zhc/u/Base128;->check_extracted(Ljava/lang/String;Ljava/io/File;Ljava/io/File;)[Ljava/lang/String;

    move-result-object v2

    .line 244
    :cond_33
    return-object v2
.end method

.method public static encode2(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    .registers 14
    .param p0, "in"    # Ljava/io/InputStream;
    .param p1, "Dest_out"    # Ljava/io/OutputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const-wide/16 v10, 0x405

    .line 321
    invoke-virtual {p0}, Ljava/io/InputStream;->available()I

    move-result v9

    int-to-long v6, v9

    .local v6, "fS":J
    div-long v2, v6, v10

    .line 322
    .local v2, "a":J
    rem-long v10, v6, v10

    long-to-int v1, v10

    .line 323
    .local v1, "b":I
    const/16 v9, 0x405

    new-array v8, v9, [B

    .local v8, "r":[B
    const/16 v9, 0x498

    new-array v0, v9, [B

    .line 324
    .local v0, "R":[B
    const/16 v9, 0x8

    new-array v4, v9, [B

    fill-array-data v4, :array_34

    .line 325
    .local v4, "b1":[B
    const/4 v9, 0x0

    const-wide/16 v10, 0x7

    rem-long v10, v6, v10

    long-to-int v10, v10

    int-to-byte v10, v10

    aput-byte v10, v4, v9

    .line 326
    invoke-virtual {p1, v4}, Ljava/io/OutputStream;->write([B)V

    .line 327
    const/4 v5, 0x0

    .local v5, "i":I
    :goto_28
    int-to-long v10, v5

    cmp-long v9, v10, v2

    if-gez v9, :cond_33

    .line 329
    invoke-virtual {p0, v8}, Ljava/io/InputStream;->read([B)I

    .line 327
    add-int/lit8 v5, v5, 0x1

    goto :goto_28

    .line 332
    :cond_33
    return-void

    .line 324
    :array_34
    .array-data 1
        0x0t
        0x0t
        0x0t
        0x0t
        0x0t
        0x7at
        0x68t
        0x63t
    .end array-data
.end method

.method private encode_extracted(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    .registers 15
    .param p1, "in"    # Ljava/io/InputStream;
    .param p2, "to_out"    # Ljava/io/OutputStream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 159
    const-wide/16 v4, 0x0

    .line 160
    .local v4, "i":J
    invoke-virtual {p1}, Ljava/io/InputStream;->available()I

    move-result v6

    int-to-long v6, v6

    sput-wide v6, Lcom/zhc/u/Base128;->FileSize:J

    .line 162
    const/4 v6, 0x7

    new-array v2, v6, [I

    .line 163
    .local v2, "b_i":[I
    const/16 v6, 0x8

    new-array v1, v6, [B

    fill-array-data v1, :array_86

    .line 164
    .local v1, "b1":[B
    const/4 v6, 0x0

    sget-wide v8, Lcom/zhc/u/Base128;->FileSize:J

    const-wide/16 v10, 0x7

    rem-long/2addr v8, v10

    long-to-int v7, v8

    int-to-byte v7, v7

    aput-byte v7, v1, v6

    .line 165
    invoke-virtual {p2, v1}, Ljava/io/OutputStream;->write([B)V

    .line 167
    :goto_20
    const/4 v6, 0x7

    new-array v0, v6, [B

    .line 168
    .local v0, "b":[B
    invoke-virtual {p1, v0}, Ljava/io/InputStream;->read([B)I

    move-result v6

    const/4 v7, -0x1

    if-eq v6, v7, :cond_78

    .line 169
    const-wide/16 v6, 0x7

    add-long/2addr v4, v6

    .line 170
    const-wide/32 v6, 0xffffc

    rem-long v6, v4, v6

    const-wide/16 v8, 0x0

    cmp-long v6, v6, v8

    if-nez v6, :cond_5e

    .line 171
    sget-object v6, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "progress: "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    long-to-double v8, v4

    sget-wide v10, Lcom/zhc/u/Base128;->FileSize:J

    long-to-double v10, v10

    div-double/2addr v8, v10

    const-wide/high16 v10, 0x4059000000000000L    # 100.0

    mul-double/2addr v8, v10

    invoke-virtual {v7, v8, v9}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, "%"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 173
    :cond_5e
    const/4 v3, 0x0

    .local v3, "j":I
    :goto_5f
    array-length v6, v0

    if-ge v3, v6, :cond_6b

    .line 174
    aget-byte v6, v0, v3

    and-int/lit16 v6, v6, 0xff

    aput v6, v2, v3

    .line 173
    add-int/lit8 v3, v3, 0x1

    goto :goto_5f

    .line 176
    :cond_6b
    new-instance v6, Lcom/zhc/u/Base128;

    invoke-direct {v6}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v6, v2}, Lcom/zhc/u/Base128;->_7Return_8([I)[B

    move-result-object v6

    invoke-virtual {p2, v6}, Ljava/io/OutputStream;->write([B)V

    goto :goto_20

    .line 179
    .end local v3    # "j":I
    :cond_78
    invoke-virtual {p2}, Ljava/io/OutputStream;->flush()V

    .line 180
    invoke-virtual {p2}, Ljava/io/OutputStream;->close()V

    .line 181
    sget-object v6, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v7, "progress: 100%\n\u7f16\u7801\u6210\u529f\uff01"

    invoke-virtual {v6, v7}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 183
    return-void

    .line 163
    :array_86
    .array-data 1
        -0x1t
        0x0t
        0x0t
        0x0t
        0x0t
        0x7at
        0x68t
        0x63t
    .end array-data
.end method

.method public static encode_text(Ljava/lang/String;)[B
    .registers 6
    .param p0, "string"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    .line 310
    invoke-virtual {p0}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    .line 311
    .local v0, "b":[B
    array-length v4, v0

    move v2, v3

    :goto_7
    if-ge v2, v4, :cond_1a

    aget-byte v1, v0, v2

    .line 312
    .local v1, "b1":B
    if-gez v1, :cond_17

    .line 313
    sget-object v2, Ljava/lang/System;->err:Ljava/io/PrintStream;

    const-string v4, "\u5b57\u7b26\u4e32\u542b\u6709\u975eASCII\u5185\u5b57\u7b26\uff0c\u56e0\u6b64\u9700\u8981\u6307\u5b9a\u7f16\u7801\u3002"

    invoke-virtual {v2, v4}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 314
    new-array v2, v3, [B

    .line 317
    .end local v1    # "b1":B
    :goto_16
    return-object v2

    .line 311
    .restart local v1    # "b1":B
    :cond_17
    add-int/lit8 v2, v2, 0x1

    goto :goto_7

    .line 317
    .end local v1    # "b1":B
    :cond_1a
    invoke-static {v0}, Lcom/zhc/u/Base128;->encode_text([B)[B

    move-result-object v2

    goto :goto_16
.end method

.method public static encode_text(Ljava/lang/String;Ljava/lang/String;)[B
    .registers 4
    .param p0, "string"    # Ljava/lang/String;
    .param p1, "charset"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 300
    invoke-virtual {p0, p1}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v0

    .line 301
    .local v0, "b":[B
    invoke-static {v0}, Lcom/zhc/u/Base128;->encode_text([B)[B

    move-result-object v1

    return-object v1
.end method

.method public static encode_text(Ljava/lang/String;Ljava/nio/charset/Charset;)[B
    .registers 4
    .param p0, "string"    # Ljava/lang/String;
    .param p1, "charset"    # Ljava/nio/charset/Charset;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 305
    invoke-virtual {p0, p1}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v0

    .line 306
    .local v0, "b":[B
    invoke-static {v0}, Lcom/zhc/u/Base128;->encode_text([B)[B

    move-result-object v1

    return-object v1
.end method

.method public static encode_text([B)[B
    .registers 4
    .param p0, "bytes"    # [B
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 291
    new-instance v0, Ljava/io/ByteArrayInputStream;

    invoke-direct {v0, p0}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    .line 292
    .local v0, "bais":Ljava/io/ByteArrayInputStream;
    new-instance v1, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v1}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 293
    .local v1, "baos":Ljava/io/ByteArrayOutputStream;
    invoke-static {v0, v1}, Lcom/zhc/u/Base128;->encode(Ljava/io/InputStream;Ljava/io/OutputStream;)V

    .line 294
    invoke-virtual {v0}, Ljava/io/ByteArrayInputStream;->close()V

    .line 295
    invoke-virtual {v1}, Ljava/io/ByteArrayOutputStream;->close()V

    .line 296
    invoke-virtual {v1}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v2

    return-object v2
.end method

.method public static pers.zhc.u.kotlin.main([Ljava/lang/String;)V
    .registers 16
    .param p0, "args"    # [Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v11, 0x1

    const/4 v10, 0x0

    const/4 v14, 0x3

    .line 52
    array-length v12, p0

    if-eq v12, v14, :cond_21

    array-length v12, p0

    const/4 v13, 0x4

    if-ne v12, v13, :cond_18

    aget-object v12, p0, v14

    if-eqz v12, :cond_18

    aget-object v12, p0, v14

    const-string v13, ""

    invoke-virtual {v12, v13}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v12

    if-eqz v12, :cond_21

    .line 53
    :cond_18
    new-instance v10, Lcom/zhc/u/Base128;

    invoke-direct {v10}, Lcom/zhc/u/Base128;-><init>()V

    invoke-direct {v10}, Lcom/zhc/u/Base128;->tip()V

    .line 77
    :goto_20
    return-void

    .line 55
    :cond_21
    aget-object v0, p0, v10

    .line 56
    .local v0, "arg0":Ljava/lang/String;
    aget-object v1, p0, v11

    .line 57
    .local v1, "arg1":Ljava/lang/String;
    const/4 v12, 0x2

    aget-object v2, p0, v12

    .line 60
    .local v2, "arg2":Ljava/lang/String;
    const/4 v12, 0x3

    :try_start_29
    aget-object v3, p0, v12
    :try_end_2b
    .catch Ljava/lang/Exception; {:try_start_29 .. :try_end_2b} :catch_6b

    .line 64
    .local v3, "arg3":Ljava/lang/String;
    :goto_2b
    const-string v12, "Y"

    invoke-virtual {v3, v12}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v12

    if-nez v12, :cond_3b

    const-string v12, "y"

    invoke-virtual {v3, v12}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v12

    if-eqz v12, :cond_6f

    :cond_3b
    move v4, v11

    .line 65
    .local v4, "ck":Z
    :goto_3c
    new-instance v7, Ljava/io/File;

    invoke-direct {v7, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .local v7, "f1":Ljava/io/File;
    new-instance v8, Ljava/io/File;

    invoke-direct {v8, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 66
    .local v8, "f2":Ljava/io/File;
    const/4 v12, -0x1

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v13

    sparse-switch v13, :sswitch_data_9c

    :cond_4e
    move v10, v12

    :goto_4f
    packed-switch v10, :pswitch_data_a6

    goto :goto_20

    .line 68
    :pswitch_53
    invoke-static {v7, v8, v4}, Lcom/zhc/u/Base128;->encode(Ljava/io/File;Ljava/io/File;Z)[Ljava/lang/String;

    move-result-object v10

    invoke-static {v10}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    .line 69
    .local v6, "enR":Ljava/lang/String;
    sget-object v10, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v11, "[]"

    invoke-virtual {v6, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_67

    const-string v6, ""

    .end local v6    # "enR":Ljava/lang/String;
    :cond_67
    invoke-virtual {v10, v6}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_20

    .line 61
    .end local v3    # "arg3":Ljava/lang/String;
    .end local v4    # "ck":Z
    .end local v7    # "f1":Ljava/io/File;
    .end local v8    # "f2":Ljava/io/File;
    :catch_6b
    move-exception v9

    .line 62
    .local v9, "ignored":Ljava/lang/Exception;
    const-string v3, "n"

    .restart local v3    # "arg3":Ljava/lang/String;
    goto :goto_2b

    .end local v9    # "ignored":Ljava/lang/Exception;
    :cond_6f
    move v4, v10

    .line 64
    goto :goto_3c

    .line 66
    .restart local v4    # "ck":Z
    .restart local v7    # "f1":Ljava/io/File;
    .restart local v8    # "f2":Ljava/io/File;
    :sswitch_71
    const-string v11, "-encode"

    invoke-virtual {v0, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_4e

    goto :goto_4f

    :sswitch_7a
    const-string v10, "-decode"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_4e

    move v10, v11

    goto :goto_4f

    .line 72
    :pswitch_84
    invoke-static {v7, v8, v4}, Lcom/zhc/u/Base128;->decode(Ljava/io/File;Ljava/io/File;Z)[Ljava/lang/String;

    move-result-object v10

    invoke-static {v10}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    .line 73
    .local v5, "deR":Ljava/lang/String;
    sget-object v10, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v11, "[]"

    invoke-virtual {v5, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_98

    const-string v5, ""

    .end local v5    # "deR":Ljava/lang/String;
    :cond_98
    invoke-virtual {v10, v5}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_20

    .line 66
    :sswitch_data_9c
    .sparse-switch
        -0x32503a5 -> :sswitch_7a
        -0xf1577d -> :sswitch_71
    .end sparse-switch

    :pswitch_data_a6
    .packed-switch 0x0
        :pswitch_53
        :pswitch_84
    .end packed-switch
.end method

.method private tip()V
    .registers 3

    .prologue
    .line 283
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v1, "Base128\u7f16\u7801\n\u547d\u4ee4\u884c\u53c2\u6570\u683c\u5f0f\uff1aCommand [-encode] [-decode] [\u9700\u8981\u7f16\u7801\u7684\u6587\u4ef6\u4f4d\u7f6e] [\u7f16\u7801\u540e\u751f\u6210\u7684\u6587\u4ef6\u4f4d\u7f6e\uff08\u4e0d\u5b58\u5728\u5219\u521b\u5efa\uff09] [\u5b8c\u6210\u540e\u662f\u5426\u6821\u9a8c\uff08y:\u662f | n:\u5426\uff09\uff08\u9ed8\u8ba4\u4e3an\uff09]\n\u53c2\u6570\u4e2d\u6709\u7a7a\u683c\u9700\u52a0\u53cc\u5f15\u53f7"

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 288
    return-void
.end method


# virtual methods
.method public NumStr_lenTo(I)Ljava/lang/String;
    .registers 6
    .param p1, "to"    # I

    .prologue
    .line 199
    const/4 v1, 0x0

    .line 200
    .local v1, "j":Z
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    .line 201
    .local v2, "sb":Ljava/lang/StringBuilder;
    iget-object v3, p0, Lcom/zhc/u/Base128;->string:Ljava/lang/String;

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    if-ge v3, p1, :cond_26

    .line 202
    const/4 v1, 0x1

    .line 203
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_10
    iget-object v3, p0, Lcom/zhc/u/Base128;->string:Ljava/lang/String;

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    sub-int v3, p1, v3

    if-ge v0, v3, :cond_21

    .line 204
    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 203
    add-int/lit8 v0, v0, 0x1

    goto :goto_10

    .line 206
    :cond_21
    iget-object v3, p0, Lcom/zhc/u/Base128;->string:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 208
    .end local v0    # "i":I
    :cond_26
    if-eqz v1, :cond_2d

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    :goto_2c
    return-object v3

    :cond_2d
    iget-object v3, p0, Lcom/zhc/u/Base128;->string:Ljava/lang/String;

    goto :goto_2c
.end method

.method public d1([B)[B
    .registers 11
    .param p1, "buf"    # [B

    .prologue
    const/4 v8, 0x5

    const/4 v7, 0x4

    const/4 v6, 0x3

    const/4 v5, 0x2

    const/4 v4, 0x1

    .line 348
    const/4 v1, 0x7

    new-array v0, v1, [B

    .line 349
    .local v0, "r":[B
    const/4 v1, 0x0

    const/4 v2, 0x0

    aget-byte v2, p1, v2

    and-int/lit16 v2, v2, 0xff

    shl-int/lit8 v2, v2, 0x1

    aget-byte v3, p1, v4

    and-int/lit16 v3, v3, 0xff

    shr-int/lit8 v3, v3, 0x6

    or-int/2addr v2, v3

    int-to-byte v2, v2

    aput-byte v2, v0, v1

    .line 350
    aget-byte v1, p1, v4

    and-int/lit16 v1, v1, 0xff

    shl-int/lit8 v1, v1, 0x2

    aget-byte v2, p1, v5

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x5

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v4

    .line 351
    aget-byte v1, p1, v5

    and-int/lit16 v1, v1, 0xff

    shl-int/lit8 v1, v1, 0x3

    aget-byte v2, p1, v6

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x4

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v5

    .line 352
    aget-byte v1, p1, v6

    and-int/lit16 v1, v1, 0xff

    shl-int/lit8 v1, v1, 0x4

    aget-byte v2, p1, v7

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x3

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v6

    .line 353
    aget-byte v1, p1, v7

    and-int/lit16 v1, v1, 0xff

    shl-int/lit8 v1, v1, 0x5

    aget-byte v2, p1, v8

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x2

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v7

    .line 354
    aget-byte v1, p1, v8

    and-int/lit16 v1, v1, 0xff

    shl-int/lit8 v1, v1, 0x6

    const/4 v2, 0x6

    aget-byte v2, p1, v2

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x1

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v8

    .line 355
    const/4 v1, 0x6

    const/4 v2, 0x6

    aget-byte v2, p1, v2

    and-int/lit16 v2, v2, 0xff

    shl-int/lit8 v2, v2, 0x7

    const/4 v3, 0x7

    aget-byte v3, p1, v3

    and-int/lit16 v3, v3, 0xff

    or-int/2addr v2, v3

    int-to-byte v2, v2

    aput-byte v2, v0, v1

    .line 356
    return-object v0
.end method

.method public e1([B)[B
    .registers 9
    .param p1, "buf"    # [B

    .prologue
    const/4 v6, 0x4

    const/4 v5, 0x3

    const/4 v4, 0x2

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 335
    const/16 v1, 0x8

    new-array v0, v1, [B

    .line 336
    .local v0, "r":[B
    aget-byte v1, p1, v2

    and-int/lit16 v1, v1, 0xff

    shr-int/lit8 v1, v1, 0x1

    int-to-byte v1, v1

    aput-byte v1, v0, v2

    .line 337
    aget-byte v1, p1, v2

    and-int/lit8 v1, v1, 0x1

    shl-int/lit8 v1, v1, 0x6

    aget-byte v2, p1, v3

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x2

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v3

    .line 338
    aget-byte v1, p1, v3

    and-int/lit8 v1, v1, 0x3

    shl-int/lit8 v1, v1, 0x5

    aget-byte v2, p1, v4

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x3

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v4

    .line 339
    aget-byte v1, p1, v4

    and-int/lit8 v1, v1, 0x7

    shl-int/lit8 v1, v1, 0x4

    aget-byte v2, p1, v5

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x4

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v5

    .line 340
    aget-byte v1, p1, v5

    and-int/lit8 v1, v1, 0xf

    shl-int/lit8 v1, v1, 0x3

    aget-byte v2, p1, v6

    and-int/lit16 v2, v2, 0xff

    shr-int/lit8 v2, v2, 0x5

    or-int/2addr v1, v2

    int-to-byte v1, v1

    aput-byte v1, v0, v6

    .line 341
    const/4 v1, 0x5

    aget-byte v2, p1, v6

    and-int/lit8 v2, v2, 0x1f

    shl-int/lit8 v2, v2, 0x2

    const/4 v3, 0x5

    aget-byte v3, p1, v3

    and-int/lit16 v3, v3, 0xff

    shr-int/lit8 v3, v3, 0x6

    or-int/2addr v2, v3

    int-to-byte v2, v2

    aput-byte v2, v0, v1

    .line 342
    const/4 v1, 0x6

    const/4 v2, 0x5

    aget-byte v2, p1, v2

    and-int/lit8 v2, v2, 0x3f

    shl-int/lit8 v2, v2, 0x1

    const/4 v3, 0x6

    aget-byte v3, p1, v3

    and-int/lit16 v3, v3, 0xff

    shr-int/lit8 v3, v3, 0x7

    or-int/2addr v2, v3

    int-to-byte v2, v2

    aput-byte v2, v0, v1

    .line 343
    const/4 v1, 0x7

    const/4 v2, 0x6

    aget-byte v2, p1, v2

    and-int/lit8 v2, v2, 0x7f

    int-to-byte v2, v2

    aput-byte v2, v0, v1

    .line 344
    return-object v0
.end method

.method e_1029P([BI)I
    .registers 13
    .param p1, "buf"    # [B
    .param p2, "readSize"    # I

    .prologue
    .line 360
    div-int/lit8 v1, p2, 0x7

    .local v1, "a":I
    rem-int/lit8 v2, p2, 0x7

    .local v2, "b":I
    if-nez v2, :cond_23

    move v4, v1

    .local v4, "g":I
    :goto_7
    mul-int/lit8 v8, v4, 0x8

    .line 361
    .local v8, "rL":I
    const/4 v6, 0x0

    .local v6, "i":I
    :goto_a
    if-ge v6, v4, :cond_2c

    .line 362
    const/4 v9, 0x7

    new-array v3, v9, [B

    .local v3, "e_buf":[B
    const/16 v9, 0x498

    new-array v0, v9, [B

    .line 363
    .local v0, "R":[B
    const/4 v5, 0x0

    .line 364
    .local v5, "h":I
    mul-int/lit8 v7, v6, 0x7

    .local v7, "j":I
    :goto_16
    mul-int/lit8 v9, v6, 0x8

    if-ge v7, v9, :cond_26

    .line 365
    aget-byte v9, p1, v7

    aput-byte v9, v3, v5

    .line 366
    add-int/lit8 v5, v5, 0x1

    .line 364
    add-int/lit8 v7, v7, 0x1

    goto :goto_16

    .line 360
    .end local v0    # "R":[B
    .end local v3    # "e_buf":[B
    .end local v4    # "g":I
    .end local v5    # "h":I
    .end local v6    # "i":I
    .end local v7    # "j":I
    .end local v8    # "rL":I
    :cond_23
    add-int/lit8 v4, v1, 0x1

    goto :goto_7

    .line 368
    .restart local v0    # "R":[B
    .restart local v3    # "e_buf":[B
    .restart local v4    # "g":I
    .restart local v5    # "h":I
    .restart local v6    # "i":I
    .restart local v7    # "j":I
    .restart local v8    # "rL":I
    :cond_26
    invoke-virtual {p0, v3}, Lcom/zhc/u/Base128;->e1([B)[B

    .line 361
    add-int/lit8 v6, v6, 0x1

    goto :goto_a

    .line 370
    .end local v0    # "R":[B
    .end local v3    # "e_buf":[B
    .end local v5    # "h":I
    .end local v7    # "j":I
    :cond_2c
    return v8
.end method
