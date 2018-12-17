package defaultmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import defaultmod.DefaultMod;
import defaultmod.patches.AbstractCardEnum;
import defaultmod.powers.CommonPower;
import defaultmod.powers.Mana;
import com.megacrit.cardcrawl.powers.PoisonPower;
import defaultmod.powers.DecayPower;

public class SealTheWounds extends CustomCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 * 
	 * Hold Place Gain 1(2) Keywords(s).
	 */

	// TEXT DECLARATION

	public static final String ID = defaultmod.DefaultMod.makeID("SealTheWounds");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = DefaultMod.makePath(DefaultMod.DEFAULT_UNCOMMON_POWER);

	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	// /TEXT DECLARATION/

	// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.DEFAULT_GRAY;

	private static final int COST = 1;
	private static final int MAGIC = 1;
	private static int CurrPoi;
	// /STAT DECLARATION/

	public SealTheWounds() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = MAGIC;
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		if (p.hasPower(PoisonPower.POWER_ID)) {

			if (p.getPower(PoisonPower.POWER_ID).amount > 0) {

				if (!this.upgraded) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
							new DecayPower(p, p, (p.getPower(PoisonPower.POWER_ID).amount / 2)),
							p.getPower(PoisonPower.POWER_ID).amount / 2));

					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
							new PoisonPower(p, p, -(p.getPower(PoisonPower.POWER_ID).amount / 2)),
							-p.getPower(PoisonPower.POWER_ID).amount / 2));

				} else {

					AbstractDungeon.actionManager.addToBottom(
							new ApplyPowerAction(p, p, new DecayPower(p, p, (p.getPower(PoisonPower.POWER_ID).amount)),
									p.getPower(PoisonPower.POWER_ID).amount));

					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
							new PoisonPower(p, p, -(p.getPower(PoisonPower.POWER_ID).amount)),
							-p.getPower(PoisonPower.POWER_ID).amount));

				}

				if (p.getPower(PoisonPower.POWER_ID).amount < 1) {

					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Poison"));
				}
			}
		}
	}

	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new SealTheWounds();
	}

	// Upgraded stats.
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
			this.initializeDescription();
		}
	}
}