package com.summer.framework.service.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

public class GameBean
{
	private List<GameData> games = new ArrayList<GameData>();
	
	public List<GameData> getGames()
	{
		return games;
	}
	
	public class GameData
	{
		private String gameID;
		private String gameName;
		private String logoURL;
		private String initial;
		
		public GameData(String gameName)
		{
			this.gameName = gameName;
		}
		
		public String getGameID()
		{
			return gameID;
		}
		
		public void setGameID(String gameID)
		{
			this.gameID = gameID;
		}
		
		public String getGameName()
		{
			return gameName;
		}
		
		public void setGameName(String gameName)
		{
			this.gameName = gameName;
		}
		
		public String getLogoURL()
		{
			return logoURL;
		}
		
		public void setLogoURL(String logoURL)
		{
			this.logoURL = logoURL;
		}
		
		public String getInitial()
		{
			return initial;
		}
		
		public void setInitial(String initial)
		{
			this.initial = initial;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if (o == null)
			{
				return false;
			}
			if (this == o)
			{
				return true;
			}
			if (o instanceof GameBean)
			{
				return new EqualsBuilder().append(gameName, ((GameData) o).getGameName()).isEquals();
			}
			return false;
		}
		
		@Override
		public String toString()
		{
			return gameName;
		}
	}
}
