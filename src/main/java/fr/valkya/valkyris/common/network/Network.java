package fr.valkya.valkyris.common.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.valkya.valkyris.References;
import fr.valkya.valkyris.common.network.packets.*;
import fr.valkya.valkyris.common.network.packets.market.*;

public class Network extends SimpleNetworkWrapper {

	private static int id;
	
	public Network() {
		super(References.MODID);
		
		/**
		 * Faction
		 * 
		this.registerMessage(PacketCreateFaction.Handler.class, PacketCreateFaction.class, id++, Side.SERVER);
		this.registerMessage(PacketRequestFactionInfos.Handler.class, PacketRequestFactionInfos.class, id++, Side.SERVER);
		this.registerMessage(PacketResponseFactionInfos.Handler.class, PacketResponseFactionInfos.class, id++, Side.CLIENT);
		this.registerMessage(PacketClaimFaction.Handler.class, PacketClaimFaction.class, id++, Side.SERVER);
		this.registerMessage(PacketUnclaimFaction.Handler.class, PacketUnclaimFaction.class, id++, Side.SERVER);
		this.registerMessage(PacketInvitePlayer.Handler.class, PacketInvitePlayer.class, id++, Side.SERVER);
		this.registerMessage(PacketKickPlayer.Handler.class, PacketKickPlayer.class, id++, Side.SERVER);
		this.registerMessage(PacketPromotePlayer.Handler.class, PacketPromotePlayer.class, id++, Side.SERVER);
		this.registerMessage(PacketDemotePlayer.Handler.class, PacketDemotePlayer.class, id++, Side.SERVER);
		this.registerMessage(PacketLeadPlayer.Handler.class, PacketLeadPlayer.class, id++, Side.SERVER);
		this.registerMessage(PacketDisbandFaction.Handler.class, PacketDisbandFaction.class, id++, Side.SERVER);
		this.registerMessage(PacketDebugFaction.Handler.class, PacketDebugFaction.class, id++, Side.SERVER);
		*/
		
		/**
		 * Le reste
		 */
		this.registerMessage(PacketUpdateSize.Handler.class, PacketUpdateSize.class, id++, Side.CLIENT);
		this.registerMessage(PacketNotify.Handler.class, PacketNotify.class, id++, Side.CLIENT);
		this.registerMessage(PacketTitle.Handler.class, PacketTitle.class, id++, Side.CLIENT);
		this.registerMessage(PacketBroadcast.Handler.class, PacketBroadcast.class, id++, Side.CLIENT);
		
		this.registerMessage(PacketESPHelmet.Handler.class, PacketESPHelmet.class, id++, Side.SERVER);
		
		this.registerMessage(PacketSyncSize.Handler.class, PacketSyncSize.class, id++, Side.CLIENT);
		
		this.registerMessage(PacketOpenMacroSelector.Handler.class, PacketOpenMacroSelector.class, id++, Side.CLIENT);
	}
	
	public void registerMarketMessages() {
		this.registerMessage(PacketRequestListings.Handler.class, PacketRequestListings.class, id++, Side.SERVER);
		this.registerMessage(PacketProduct.Handler.class, PacketProduct.class, id++, Side.CLIENT);
		this.registerMessage(PacketTransactionRequest.Handler.class, PacketTransactionRequest.class, id++, Side.SERVER);
		this.registerMessage(PacketTransactionResponse.Handler.class, PacketTransactionResponse.class, id++, Side.CLIENT);
	}

}
