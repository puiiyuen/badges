import { OpenAPIRoute, Str } from 'chanfana'
import { z } from 'zod'

import { rankingBadge } from '../../services/ranking'

export class Ranking extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode v1'],
    request: {
      params: z.object({
        username: Str({
          required: true,
        }),
      }),
    },
    responses: {
      200: {
        description: 'Successful Response',
        content: {
          'img/svg+xml': {
            schema: Str(),
          },
        },
      },
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const shield = await rankingBadge(username)
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
