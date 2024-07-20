package com.chailotl.particular.sushi_bar.mixin.lavender;

import com.chailotl.particular.sushi_bar.lavender.SushiBook;
import io.wispforest.lavender.book.Book;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;

@Pseudo
@Mixin(Book.class)
public class InjectBook implements SushiBook
{
	@Unique
	private boolean enableSushiFeatures = false;

	@Override
	public void enableSushiFeatures()
	{
		enableSushiFeatures = true;
	}

	@Override
	public boolean getSushiFeatures()
	{
		return enableSushiFeatures;
	}
}