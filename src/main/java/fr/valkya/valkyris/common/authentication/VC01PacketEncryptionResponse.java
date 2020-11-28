package fr.valkya.valkyris.common.authentication;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

import javax.crypto.SecretKey;

import com.google.common.base.Charsets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.util.CryptManager;

public class VC01PacketEncryptionResponse extends C01PacketEncryptionResponse
{
	private byte[] encryptedAccessToken = new byte[0];
	private byte[] encryptedUUID = new byte[0];
    private byte[] field_149302_a = new byte[0];
    private byte[] field_149301_b = new byte[0];
    
    public VC01PacketEncryptionResponse() {}

    @SideOnly(Side.CLIENT)
    public VC01PacketEncryptionResponse(SecretKey p_i45271_1_, PublicKey p_i45271_2_, byte[] p_i45271_3_, UUID uuid, String at)
    {
    	this.encryptedAccessToken = Charsets.UTF_8.encode(at).array();
    	this.encryptedUUID = Charsets.UTF_8.encode(uuid.toString()).array();
        this.field_149302_a = CryptManager.encryptData(p_i45271_2_, p_i45271_1_.getEncoded());
        this.field_149301_b = CryptManager.encryptData(p_i45271_2_, p_i45271_3_);
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer p_148837_1_) throws IOException
    {
    	this.encryptedAccessToken = readBlob(p_148837_1_);
    	this.encryptedUUID = readBlob(p_148837_1_);
        this.field_149302_a = readBlob(p_148837_1_);
        this.field_149301_b = readBlob(p_148837_1_);
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer p_148840_1_) throws IOException
    {
    	writeBlob(p_148840_1_, this.encryptedAccessToken);
    	writeBlob(p_148840_1_, encryptedUUID);
        writeBlob(p_148840_1_, this.field_149302_a);
        writeBlob(p_148840_1_, this.field_149301_b);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerLoginServer p_148833_1_)
    {
        p_148833_1_.processEncryptionResponse(this);
    }

    public SecretKey func_149300_a(PrivateKey p_149300_1_)
    {
        return CryptManager.decryptSharedKey(p_149300_1_, this.field_149302_a);
    }

    public byte[] func_149299_b(PrivateKey p_149299_1_)
    {
        return p_149299_1_ == null ? this.field_149301_b : CryptManager.decryptData(p_149299_1_, this.field_149301_b);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandler p_148833_1_)
    {
        this.processPacket((INetHandlerLoginServer)p_148833_1_);
    }
    
    public byte[] getAccessToken() {
    	return encryptedAccessToken;
    }
    
    public byte[] getUUID() {
    	return encryptedUUID;
    }
}