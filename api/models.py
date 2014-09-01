import rom


class Point(rom.Model):
    id = rom.PrimaryKey(index=True)
    lat = rom.Float()
    lng = rom.Float()
    title = rom.String()
